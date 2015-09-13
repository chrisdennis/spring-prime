package org.terracotta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PrimeController {
  
  @Autowired IsPrimeService isPrimeService;
  @Autowired NthPrimeService nthPrimeService;

  @RequestMapping("/prime/{index}")
  public @ResponseBody String nthPrime(@PathVariable("index") int index) {
    return "Prime #" + index + " is " + nthPrimeService.primeNumber(index) + "\n";
  }

  @RequestMapping("/isprime/{index}")
  public @ResponseBody String isPrime(@PathVariable("index") int index) {
    return index + " is " + (isPrimeService.isPrime(index) ? "prime" : "not prime") + "\n";
  }
}
