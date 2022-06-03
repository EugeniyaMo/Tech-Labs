package ru.itmo.kotikiservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.kotikiservices.models.Cat;

public interface CatRepository extends JpaRepository<Cat, Integer> {
    Cat save(Cat cat);
    Cat findById(int id);
    void delete(Cat cat);
}
