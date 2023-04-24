package com.citi.assignment.service.impl;

import com.citi.assignment.service.DataPersistenceService;
import com.citi.assignment.service.DataValidationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import static com.citi.assignment.constant.Constants.DB_PERSISTENCE_SERVICE;

@Service
@Slf4j
public class DataValidationServiceImpl implements DataValidationService {
    private static final String[] REGEX = {"^[a-zA-Z]+$"};

    @Autowired
    @Qualifier(DB_PERSISTENCE_SERVICE)
    private DataPersistenceService dataService;

    @Override
    public boolean validateForPalindrome(String input) {
        if (Arrays.stream(REGEX).anyMatch(input::matches)) {
            if (dataService.getPersistedEntries().stream()
                    .anyMatch(x -> x.getValue().equalsIgnoreCase(input))) {
                return true;
            } else if (input.equalsIgnoreCase(StringUtils.reverse(input))) {
                CompletableFuture.runAsync(() -> dataService.persistValidInput(input));
                return true;
            }
        }
        return false;
    }
}