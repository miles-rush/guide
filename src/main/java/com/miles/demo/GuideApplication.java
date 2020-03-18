package com.miles.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class GuideApplication {

    public static void main(String[] args) {

        SpringApplication.run(GuideApplication.class, args);
    }

}
