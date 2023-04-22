package com.citi.assignment.service.impl;

import com.citi.assignment.service.MyDataService;
import com.citi.assignment.service.MyDataValidationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class MyDataValidationServiceImpl implements MyDataValidationService {
    private static final String[] REGEX = {"^[a-zA-Z]*$"};

    @Autowired
    private MyDataService dataService;

    @Override
    public boolean validateForPalindrome(String input) {
        if (Arrays.stream(REGEX).anyMatch(input::matches)) {
            if (dataService.getPersistedEntries().stream()
                    .anyMatch(x -> x.getValue().equalsIgnoreCase(input))) {
                return true;
            } else if (input.equalsIgnoreCase(StringUtils.reverse(input))){
                CompletableFuture.runAsync(() -> dataService.persistValidInput(input));
                return true;
            }
        }
        return false;
    }
}
