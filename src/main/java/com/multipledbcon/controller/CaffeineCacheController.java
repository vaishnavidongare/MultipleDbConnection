package com.multipledbcon.controller;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.benmanes.caffeine.cache.Cache;


@RestController
@RequestMapping("/cache")
public class CaffeineCacheController {

	private final CacheManager cacheManager;
    private final ObjectMapper objectMapper;

    public CaffeineCacheController(@Qualifier("employeeCacheManager") final CacheManager cacheManager) {
        this.cacheManager = cacheManager;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    @GetMapping("/name")
    public Collection<String> getCacheNames() {
        return cacheManager.getCacheNames();
    }

    @GetMapping("/entries/{cacheName}")
    public Map<String, Object> getEntriesForCache(@PathVariable(name = "cacheName") final String cacheName) throws JsonProcessingException {
		final Cache<String, Object> cache = (Cache<String, Object>) cacheManager.getCache(cacheName).getNativeCache();

        final ConcurrentMap<String, Object> data = cache.asMap();
        final String json = objectMapper.writeValueAsString(data);

        final TypeReference<HashMap<String,Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};  //TypeReference<>() {};
        return objectMapper.readValue(json, typeRef);
    }
    


}
