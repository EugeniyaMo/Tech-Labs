package ru.itmo.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itmo.models.Friendship;
import ru.itmo.repository.FriendshipRepository;
import ru.itmo.service.FriendshipService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FriendshipServiceImpl implements FriendshipService {
    private final FriendshipRepository friendshipRepository;

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
