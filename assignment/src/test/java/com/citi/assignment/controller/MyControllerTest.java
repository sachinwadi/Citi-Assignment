package com.citi.assignment.controller;

import com.citi.assignment.AbstractContextInitializr;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MyControllerTest extends AbstractContextInitializr {

    private MockRestServiceServer mockServer;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach()
    void init() {
        RestTemplate restTemplate = new RestTemplate();
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void shouldValidateInputIfPalindromeOrNot() throws Exception {

        String url = "http://localhost:8080/validateuserinput?userName=sachin&input=qiq";

        mockServer.expect(ExpectedCount.once(), requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("Input [qiq] IS a Palindrome"));

        mockMvc.perform(get("/validateuserinput").param("userName", "Sachin")
                        .param("input", "qiq"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Input [qiq] IS a Palindrome"));
    }
}