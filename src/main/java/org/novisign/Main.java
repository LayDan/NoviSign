package org.novisign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Main {
    public static void main(String[] args) {
        System.getenv().forEach((k, v) -> System.out.println(k + "=" + v));
        SpringApplication.run(Main.class, args);
    }
}