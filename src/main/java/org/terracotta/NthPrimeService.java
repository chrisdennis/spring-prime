package org.terracotta;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import static java.util.stream.LongStream.range;

@Component
public class NthPrimeService {

  @Autowired IsPrimeService isPrimeService;
  
  public long primeNumber(long index) {
    return range(1, Long.MAX_VALUE).filter(isPrimeService::isPrime).limit(index).reduce(-1L, (a, b) -> b);
  }
}
