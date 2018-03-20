package com.codecool.enterprise;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RatingMicroserviceApplication {
    public static void main(String[] args) {
        //web: java -jar target/RatingMicroService-1.0-SNAPSHOT.jar
        SpringApplication.run(RatingMicroserviceApplication.class, args);
    }
}
