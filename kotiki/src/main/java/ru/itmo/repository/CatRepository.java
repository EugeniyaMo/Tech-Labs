package ru.itmo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.models.Cat;

public interface CatRepository extends JpaRepository<Cat, Integer> {
    Cat save(Cat cat);
    Cat findById(int id);
    void delete(Cat cat);
}
