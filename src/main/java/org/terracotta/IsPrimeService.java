package org.terracotta;

import javax.cache.CacheManager;
import javax.cache.annotation.CacheResult;
import javax.cache.annotation.CacheDefaults;
import javax.cache.configuration.MutableConfiguration;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.stereotype.Component;

import static java.util.stream.LongStream.range;

@Component
@CacheDefaults(cacheName = "is-prime")
public class IsPrimeService {

  @Component
  public static class CachingSetup implements JCacheManagerCustomizer {

    @Override
    public void customize(CacheManager cacheManager) {
      cacheManager.createCache("is-prime", new MutableConfiguration<>()
              .setStoreByValue(false)
              .setStatisticsEnabled(true));
    }
  }
  
  @CacheResult
  public boolean isPrime(long number) {
    if (number <= 0) {
      throw new IllegalArgumentException();
    } else if (number == 1) {
      return false;
    } else {
      return range(2L, number).parallel().noneMatch(i -> number % i == 0);
    }
  }
  
}
