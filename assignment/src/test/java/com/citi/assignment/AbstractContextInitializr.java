package com.citi.assignment;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {AssignmentApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("junit")
@AutoConfigureMockMvc
public abstract class AbstractContextInitializr {
}
