package com.citi.assignment.service;

public interface MyDataValidationService {
    /**
     * This service method will check if the given input is palindrome or not.
     * A palindrome is a word, number, phrase, or other sequence of characters which reads the
     * same backward as forward, such as madam or kayak
     *
     * @param input
     * @return true if given input is palindrome else return false
     */
    boolean validateForPalindrome(String input);
}
