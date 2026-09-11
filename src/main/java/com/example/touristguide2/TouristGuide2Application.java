package com.example.touristguide2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.touristguide2", "Controller", "service", "repository"})
public class TouristGuide2Application {

    public static void main(String[] args) {
        SpringApplication.run(TouristGuide2Application.class, args);
    }

}
