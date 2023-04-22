package com.citi.assignment.service.impl;

import com.citi.assignment.entity.MyEntity;
import com.citi.assignment.service.MyDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MyDataValidationServiceImplTest {

    @InjectMocks
    private MyDataValidationServiceImpl validationService;

    @Mock
    private MyDataService dataService;

    @Test
    void shouldReturnTrueForPalindromeInput() {
        when(dataService.getPersistedEntries()).thenReturn(List.of(new MyEntity("DID")));
        doNothing().when(dataService).persistValidInput(anyString());

        boolean result = validationService.validateForPalindrome("MADAM");
        assertEquals(true, result);
    }

    @Test
    void shouldReturnFalseForPalindromeInput() {
        boolean result = validationService.validateForPalindrome("SIR");
        assertEquals(false, result);
    }

    @Test
    void shouldReturnTrueForPalindromeInputAlreadyInCache() {
        when(dataService.getPersistedEntries()).thenReturn(List.of(new MyEntity("DID")));

        boolean result = validationService.validateForPalindrome("DID");
        assertEquals(true, result);
        verify(dataService, never()).persistValidInput(anyString());
    }
}