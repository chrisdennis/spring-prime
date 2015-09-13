package org.terracotta;

import org.springframework.stereotype.Component;

import static java.util.stream.LongStream.range;

@Component
public class IsPrimeService {

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
