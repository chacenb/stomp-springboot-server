package com.chace.microservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j @SpringBootApplication @EnableScheduling
@OpenAPIDefinition(info = @Info(title = "FTA API", version = "2.0", description = ""))
public class CentreTechnique {

    public static void main(String[] args) {
        SpringApplication.run(CentreTechnique.class, args);
    }

}
