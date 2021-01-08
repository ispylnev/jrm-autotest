package jrm;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@Log
public class AppConfig {

    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }

}
