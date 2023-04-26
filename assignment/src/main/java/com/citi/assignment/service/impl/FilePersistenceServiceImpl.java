package com.citi.assignment.service.impl;

import com.citi.assignment.entity.MyEntity;
import com.citi.assignment.service.DataPersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.citi.assignment.constant.Constants.*;

@Service(FILE_PERSISTENCE_SERVICE)
@Slf4j
public class FilePersistenceServiceImpl implements DataPersistenceService {

    @CachePut(value = CACHE, key = "'allEntries'")
    public List<MyEntity> persistValidInput(String input) {
        log.info("I am doing some long running task...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("I am done...");

        try {
            writeToDatasource(input);
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        //This call is needed to refresh cache
        try {
            return readFromDatasource();
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        }
        return null;

    }

    @Cacheable(value = CACHE, key = "'allEntries'", cacheManager = CACHE_MANAGER)
    public List<MyEntity> getPersistedEntries() {
        log.info("Calling method getPersistedEntries...");

        try {
            return readFromDatasource();
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    private void writeToDatasource(String input) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("db/FileStorage.txt").getFile());

        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(input);
            bw.newLine();
        }
    }

    private List<MyEntity> readFromDatasource() throws FileNotFoundException {
        List<MyEntity> entries = new ArrayList<>();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("db/FileStorage.txt").getFile());

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                entries.add(MyEntity.builder().value(scanner.nextLine()).build());
            }
        }

        return entries;
    }
}