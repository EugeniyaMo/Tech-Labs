package ru.itmo.kotikiservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.kotikiservices.models.Friendship;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Integer> {
    Friendship save(Friendship friendship);
    Friendship findById(int id);
    List<Friendship> findAll();
    void deleteById(Friendship friendship);
}
