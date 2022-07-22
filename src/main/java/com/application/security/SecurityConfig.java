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
        web.ignoring().antMatchers("/images/**"); 
        super.configure(web);
    }
    
    
    
//    UserRepository repo = new UserRepository repo();
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
//    	repo.findAll();
//    	Collection<UserDetails> userDetailsList = new ArrayList<>();
//    	
//    	userDetailsList.addAll(repo.findBy());
//    	for(int i=0; i<repo.count();i++) 
//    	{
//    		userDetailsList.add((User) User.withUsername("employee").password("{noop}userpass")
//    				.roles("EMPLOYEE", "USER").build());
//    	}
    	
    	

        Collection<UserDetails> userDetailsList = new ArrayList<>();
    	userDetailsList.add(User.withUsername("employee").password("{noop}userpass")
    			.roles("EMPLOYEE", "USER").build());
    	userDetailsList.add(User.withUsername("user").password("{noop}userpass")
    			.roles("MANAGER", "USER").build());

    	
    	return new InMemoryUserDetailsManager(userDetailsList);

    }
}