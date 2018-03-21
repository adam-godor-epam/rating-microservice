package com.codecool.enterprise;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class RatingMicroserviceApplication {
    public static void main(String[] args) {
        //System.getProperties().put( "server.port", 8001 );
        SpringApplication.run(RatingMicroserviceApplication.class, args);
    }
}
