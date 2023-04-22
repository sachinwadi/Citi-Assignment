package com.citi.assignment.service.impl;

import com.citi.assignment.AbstractContextInitializr;
import com.citi.assignment.entity.MyEntity;
import com.citi.assignment.repository.MyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

import static com.citi.assignment.constant.Constants.CACHE;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;


class MyDataServiceImplTest extends AbstractContextInitializr {

    @Autowired
    private MyRepository repository;

    @SpyBean
    private MyDataServiceImpl dataService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    void shouldPullTheEntriesFromDatabaseAndCache() {
        MyEntity entity1 = new MyEntity("deed");
        MyEntity entity2 = new MyEntity("peep");
        repository.save(entity1);
        repository.save(entity2);

        List<MyEntity> entries = dataService.getPersistedEntries();
        List<MyEntity> entries1 = dataService.getPersistedEntries();

        Cache cache = cacheManager.getCache(CACHE);
        ConcurrentMap<Object, List<MyEntity>> map = (ConcurrentMap<Object, List<MyEntity>>) cache.getNativeCache();

        assertThat(map.containsValue(List.of(entity1, entity2)));

        assertEquals(2, entries.size());
        assertEquals(2, entries1.size());
        verify(dataService, atMostOnce()).getPersistedEntries();
    }
}