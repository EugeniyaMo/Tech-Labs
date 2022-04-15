package ru.itmo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itmo.dao.AbstractDao;
import ru.itmo.models.Cat;
import ru.itmo.models.Friendship;
import ru.itmo.models.Owner;
import ru.itmo.repository.IFriendshipRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FriendshipService implements IFriendshipService{
    private final IFriendshipRepository friendshipRepository;

    @Override
    public Friendship createFriendship(int idFirstCat, int idSecondCat) {
        return friendshipRepository.save(new Friendship(idFirstCat, idSecondCat));
    }

    @Override
    public Friendship saveFriendship(Friendship friendship) {
        return friendshipRepository.save(friendship);
    }

    @Override
    public Friendship getFriendshipById(int id) {
        return friendshipRepository.findById(id);
    }

    @Override
    public List<Friendship> getFriendships() {
        return friendshipRepository.findAll();
    }

    @Override
    public Friendship deleteFriendship(Friendship friendship) {
        friendshipRepository.delete(friendship);
        return friendship;
    }
}
