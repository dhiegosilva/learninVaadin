package com.application.security;

import com.application.SQL.UserRepository;
import com.application.views.main.Login;
import com.vaadin.flow.spring.security.VaadinWebSecurityConfigurerAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity 
@Configuration
public class SecurityConfig extends VaadinWebSecurityConfigurerAdapter { 
    private UserRepository repo2;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        setLoginView(http, Login.class); 
    }

    /**
     * Allows access to static resources, bypassing Spring security.
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/VAADIN/**"); 
        super.configure(web);
    }
    
    @Bean
    public UserDetailsService userDetailsService(UserRepository repo2) {
    	this.repo2=repo2;
    	
    	List<com.application.SQL.User> persons = repo2.findAll();
    	Collection<UserDetails> userDetailsList = new ArrayList<>();
    	
    	userDetailsList.add(User.withUsername("user@degussa.de").password("{noop}userpass")
    			.roles("MANAGER", "USER").build());
    	
    	for(int i=0; i<repo2.count();i++) 
    	{
    		userDetailsList.add(User.withUsername(persons.get(i).getEmail()).password("{noop}"+persons.get(i).getPassword())
    				.roles("EMPLOYEE", "USER").build());
    	}

    	return new InMemoryUserDetailsManager(userDetailsList);

    }
}