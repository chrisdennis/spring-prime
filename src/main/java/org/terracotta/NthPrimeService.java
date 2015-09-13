package org.terracotta;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.stream.LongStream.range;

@Component
@CacheDefaults(cacheName = "nth-prime")
public class NthPrimeService {

  @Autowired IsPrimeService isPrimeService;
  
  @CacheResult
  public long primeNumber(long index) {
    return range(1, Long.MAX_VALUE).filter(isPrimeService::isPrime).limit(index).reduce(-1L, (a, b) -> b);
  }
}
