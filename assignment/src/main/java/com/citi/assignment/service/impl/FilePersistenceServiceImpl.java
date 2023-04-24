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

    private static final String DATASOURCE = "assignment/src/main/resources/db/FileStorage.txt";

    @CachePut(value = CACHE, key = "#input")
    public List<MyEntity> persistValidInput(String input) {
        log.info("I am doing some long running task...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("I am done...");

        try {
            writeToFile(input);
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

    private void writeToFile(String input) throws IOException {
        try (FileWriter fw = new FileWriter(DATASOURCE, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(input);
            bw.newLine();
        }
    }

    @Cacheable(value = CACHE, cacheManager = CACHE_MANAGER)
    public List<MyEntity> getPersistedEntries() {
        log.info("Calling method getPersistedEntries...");

        try {
            return readFromDatasource();
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    private List<MyEntity> readFromDatasource() throws FileNotFoundException {
        List<MyEntity> entries = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(DATASOURCE))) {
            while (scanner.hasNextLine()) {
                entries.add(MyEntity.builder().value(scanner.nextLine()).build());
            }
        }

        return entries;
    }

//    /**
//     * @return
//     */
//    @Cacheable(value = CACHE)
//    public MyEntity getPersistedEntry(String input) {
//        log.info("Calling method getPersistedEntry...");
//        return myRepository.findByValue(input).orElse(null);
//    }
}