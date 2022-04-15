package ru.itmo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.models.Owner;

import java.util.List;

public interface IOwnerRepository extends JpaRepository<Owner, Integer> {
    Owner save(Owner owner);
    Owner findById(int id);
    List<Owner> findAll();
    void delete(Owner owner);
}
