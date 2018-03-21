package com.codecool.enterprise;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RatingMicroserviceApplication {
    public static void main(String[] args) {

        System.getProperties().put( "server.port", 8001 );
        SpringApplication.run(RatingMicroserviceApplication.class, args);
    }
}
