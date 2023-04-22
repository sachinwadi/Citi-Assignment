package com.citi.assignment.service.impl;

import com.citi.assignment.entity.MyEntity;
import com.citi.assignment.repository.MyRepository;
import com.citi.assignment.service.MyDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.citi.assignment.constant.Constants.CACHE;
import static com.citi.assignment.constant.Constants.CACHE_MANAGER;

@Service
@Slf4j
public class MyDataServiceImpl implements MyDataService {

    @Autowired
    private MyRepository myRepository;

    /**
     * @param input
     */
    @CachePut(value = CACHE, key = "#input")
    public void persistValidInput(String input) {
        log.info("I am doing some long running task...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("I am done...");
        myRepository.save(MyEntity.builder().value(input).build());
    }

    /**
     * @return
     */
    @Cacheable(value = CACHE, cacheManager = CACHE_MANAGER)
    public List<MyEntity> getPersistedEntries() {
        log.info("Calling method getPersistedEntries...");
        return myRepository.findAll();
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
