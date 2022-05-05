package ru.itmo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class KotikiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KotikiApplication.class, args);
        //new BCryptPasswordEncoder().encode("123");
    }


}
