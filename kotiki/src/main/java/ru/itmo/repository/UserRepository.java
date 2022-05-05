package ru.itmo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.models.CustomUser;

public interface UserRepository extends JpaRepository<CustomUser, Integer> {
    CustomUser findByUsername(String username);
    CustomUser findById(int userId);
}
