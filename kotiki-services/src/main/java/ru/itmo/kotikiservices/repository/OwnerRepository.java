package ru.itmo.kotikiservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.kotikiservices.models.Owner;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    Owner save(Owner owner);
    Owner findById(int id);
    List<Owner> findAll();
    void delete(Owner owner);
}
