package com.cyberdev.lumpas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class LumpasApplication {

    public static void main(String[] args) {
        SpringApplication.run(LumpasApplication.class, args);
    }

}
