package com.multipledbcon.cacheconfig;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class CacheConfig {
		@Bean(name = "employeeCacheManager")
		@Primary
		public CacheManager cacheManager() {
			CaffeineCacheManager cacheManager = new CaffeineCacheManager("employees");
			cacheManager.setCaffeine(Caffeine.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES).maximumSize(1000));
			return cacheManager;
		}
		@Bean(name = "productCacheManager")
		public CacheManager cacheManager1() {
			CaffeineCacheManager cacheManager = new CaffeineCacheManager("product");
			cacheManager.setCaffeine(Caffeine.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES).maximumSize(1000));
			return cacheManager;
		}
}
