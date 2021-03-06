package com.dpetrakopoulos.aggregationsystem.client.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.dpetrakopoulos.aggregationsystem.client"})
@EnableScheduling
public class ThriftClientApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ThriftClientApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}
