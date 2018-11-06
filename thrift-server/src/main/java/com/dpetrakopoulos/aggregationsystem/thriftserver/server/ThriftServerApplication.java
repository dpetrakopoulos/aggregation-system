package com.dpetrakopoulos.aggregationsystem.thriftserver.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.dpetrakopoulos.aggregationsystem.thriftserver"})
public class ThriftServerApplication implements CommandLineRunner {

    @Autowired
    ThriftMessageServer thriftMessageServer;

    public static void main(String[] args) {
        SpringApplication.run(ThriftServerApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        thriftMessageServer.start();
    }

}
