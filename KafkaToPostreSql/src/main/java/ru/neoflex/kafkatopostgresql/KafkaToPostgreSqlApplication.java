package ru.neoflex.kafkatopostgresql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
@SpringBootApplication
public class KafkaToPostgreSqlApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaToPostgreSqlApplication.class, args);


    }

}
