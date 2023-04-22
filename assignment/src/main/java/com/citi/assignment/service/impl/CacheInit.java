package com.citi.assignment.service.impl;

import com.citi.assignment.service.DataPersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static com.citi.assignment.constant.Constants.DB_PERSISTENCE_SERVICE;

@Component
@Slf4j
@Profile("default")
public class CacheInit implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    @Qualifier(DB_PERSISTENCE_SERVICE)
    private DataPersistenceService dataService;

    /**
     * This method will populate cache when application is ready
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Cache initialized...");
        dataService.getPersistedEntries();
    }
}
