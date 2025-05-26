package com.rehberhoca.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
public class RehberHocaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RehberHocaBackendApplication.class, args);
        System.out.println("🚀 Rehber Hoca Backend Started!");
        System.out.println("📍 API Base URL: http://localhost:8080/api");
        System.out.println("🔗 Test Login: http://localhost:8080/api/test");
    }
}
