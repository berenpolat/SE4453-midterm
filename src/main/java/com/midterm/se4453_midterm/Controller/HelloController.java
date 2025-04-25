package com.midterm.se4453_midterm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @GetMapping("/")
    public String root() {
        return "App is deployed and running.";
    }
    @GetMapping("/hello")
    public String hello() {
        try {
            String version = jdbcTemplate.queryForObject("SELECT version()", String.class);
            return "Connected to PostgreSQL! Version: " + version;
        } catch (Exception e) {
            return "Database connection failed: " + e.getMessage();
        }
    }
	
		@GetMapping("/debug-db")
	public String dbDebug() {
		try {
			return jdbcTemplate.getDataSource().getConnection().getMetaData().getURL();
		} catch (Exception e) {
			return "FAILED: " + e.getMessage();
		}
	}

}
