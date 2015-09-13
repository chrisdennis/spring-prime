package org.terracotta;

import org.springframework.stereotype.Component;

import static java.util.stream.LongStream.rangeClosed;

@Component
public class IsPrimeService {

  public boolean isPrime(long number) {
    if (number <= 0) {
      throw new IllegalArgumentException();
    } else {
      return rangeClosed(1L, number).parallel().filter(i -> number % i == 0).count() == 2;
    }
  }
  
}
