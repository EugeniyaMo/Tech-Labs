package ru.itmo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.models.Friendship;
import ru.itmo.models.Owner;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface IFriendshipRepository extends JpaRepository<Friendship, Integer> {
    Friendship save(Friendship friendship);
    Friendship findById(int id);
    List<Friendship> findAll();
    void delete(Friendship friendship);
}
