package com.github.lumunix.fursona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan({
        "com.github.lumunix.fursona"
})
@EnableAutoConfiguration
@Configuration
public class FursonaApplication {
    public static void main(String[] args) {
        SpringApplication.run(FursonaApplication.class, args);
    }

}
