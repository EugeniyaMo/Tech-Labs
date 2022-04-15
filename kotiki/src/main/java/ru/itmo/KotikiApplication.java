package ru.itmo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.itmo.models.Cat;
import ru.itmo.models.Color;
import ru.itmo.models.Owner;
import ru.itmo.service.CatService;
import ru.itmo.service.OwnerService;

import java.time.LocalDate;

@SpringBootApplication
public class KotikiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KotikiApplication.class, args);
    }



}
