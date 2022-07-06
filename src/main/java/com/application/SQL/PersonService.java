package com.application.SQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonService {

	
	@Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Person>
            findAll() {
        return jdbcTemplate.query(
            "SELECT name, lastName FROM vaadin",
                (rs, rowNum) -> new Person(rs.getString("name"), rs.getString("lastName")));
    }
}
