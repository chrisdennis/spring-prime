package org.terracotta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringPrimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPrimeApplication.class, args);
    }
}
