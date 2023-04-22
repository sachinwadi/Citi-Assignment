package com.citi.assignment.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.citi.assignment.constant.Constants.CACHE;
import static com.citi.assignment.constant.Constants.CACHE_MANAGER;

@Configuration
@EnableCaching
public class CacheManagerConfig {

    @Bean(CACHE_MANAGER)
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(List.of(new ConcurrentMapCache(CACHE)));
        return cacheManager;
    }

}
