package com.citi.assignment.controller;

import com.citi.assignment.service.MyDataValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class MyController {

    @Autowired
    private MyDataValidationService myDataValidationService;

    /**
     *
     * @param userName
     * @param input
     * @return result validating input is palindrome or not
     */
    @GetMapping("/validateuserinput")
    public ResponseEntity<String> validateInput(@RequestParam String userName, @RequestParam String input) {
        boolean result = myDataValidationService.validateForPalindrome(input);
        String message = String.format("Input [%s] %s a Palindrome", input, result ? "IS" : "IS NOT");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
