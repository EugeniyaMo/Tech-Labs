package ru.itmo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.models.CustomUser;
import ru.itmo.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findById(int id);
}
