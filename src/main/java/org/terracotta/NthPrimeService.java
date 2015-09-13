package org.terracotta;

import javax.cache.CacheManager;
import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheResult;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import javax.cache.expiry.TouchedExpiryPolicy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.stereotype.Component;

import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.stream.LongStream.range;

@Component
@CacheDefaults(cacheName = "nth-prime")
public class NthPrimeService {

  @Component
  public static class CachingSetup implements JCacheManagerCustomizer {

    @Override
    public void customize(CacheManager cacheManager) {
      cacheManager.createCache("nth-prime", new MutableConfiguration<>()
              .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(SECONDS, 5)))
              .setStoreByValue(false)
              .setStatisticsEnabled(true));
    }
  }
  
  @Autowired IsPrimeService isPrimeService;
  
  @CacheResult
  public long primeNumber(long index) {
    return range(1, Long.MAX_VALUE).filter(isPrimeService::isPrime).limit(index).reduce(-1L, (a, b) -> b);
  }
}
