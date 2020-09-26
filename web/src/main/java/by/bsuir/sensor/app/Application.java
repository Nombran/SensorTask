package by.bsuir.sensor.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "by.bsuir.sensor.**")
@EntityScan(basePackages = "by.bsuir.sensor.**")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}


