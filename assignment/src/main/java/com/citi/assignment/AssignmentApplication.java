package com.citi.assignment;

import com.citi.assignment.service.MyDataValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AssignmentApplication {

    @Value("${spring.datasource.url}")
    private String name;

    @Autowired
    private MyDataValidationService service;

    public static void main(String[] args) {
        SpringApplication.run(AssignmentApplication.class, args);
    }
}
