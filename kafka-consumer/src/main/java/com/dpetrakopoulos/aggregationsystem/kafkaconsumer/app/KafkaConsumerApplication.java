package com.dpetrakopoulos.aggregationsystem.kafkaconsumer.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication(scanBasePackages = {"com.dpetrakopoulos.aggregationsystem.kafkaconsumer"})
@EnableCassandraRepositories("com.dpetrakopoulos.aggregationsystem.kafkaconsumer.repository")
public class KafkaConsumerApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}
