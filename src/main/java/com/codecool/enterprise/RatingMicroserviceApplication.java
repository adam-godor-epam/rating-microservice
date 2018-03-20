package com.codecool.enterprise;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RatingMicroserviceApplication {
    public static void main(String[] args) {
        //web: java -cp RatingMicroService-1.0-SNAPSHOT.jar com.codecool.enterprise.RatingMicroserviceApplication
        SpringApplication.run(RatingMicroserviceApplication.class, args);
    }
}
