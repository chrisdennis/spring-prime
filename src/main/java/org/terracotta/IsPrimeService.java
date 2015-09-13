package org.terracotta;

import javax.cache.annotation.CacheResult;
import javax.cache.annotation.CacheDefaults;

import org.springframework.stereotype.Component;

import static java.util.stream.LongStream.range;

@Component
@CacheDefaults(cacheName = "is-prime")
public class IsPrimeService {

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
