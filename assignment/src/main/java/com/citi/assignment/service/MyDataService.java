package com.citi.assignment.service;

import com.citi.assignment.entity.MyEntity;

import java.util.List;

public interface MyDataService {
    /**
     * This method persists palindrome input from user to database
     * @param input
     */
    void persistValidInput(String input);

    /**
     *
     * @return list o palindrome entries previously stored in database
     */
    List<MyEntity> getPersistedEntries();
//    MyEntity getPersistedEntry(String input);
}
