package ru.itmo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.models.Friendship;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Integer> {
    Friendship save(Friendship friendship);
    Friendship findById(int id);
    List<Friendship> findAll();
    void delete(Friendship friendship);
}
