package com.citi.assignment.service.impl;

import com.citi.assignment.service.MyDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile("default")
public class CacheInit implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private MyDataService dataService;

    /**
     * This method will populate cache when application is ready
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Cache initialized...");
        dataService.getPersistedEntries();
    }
}
