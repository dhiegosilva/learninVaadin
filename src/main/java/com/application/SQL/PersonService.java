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
            "SELECT id, first_name, last_name FROM customers",
                (rs, rowNum) -> new Person(rs.getLong("id"),
                rs.getString("first_name"), rs.getString("last_name")));
    }
}
