package ru.itmo.kotikiservices.serviceImpl;

import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.itmo.kotikiservices.models.Friendship;
import ru.itmo.kotikiservices.models.Owner;
import ru.itmo.kotikiservices.repository.FriendshipRepository;
import ru.itmo.kotikiservices.service.FriendshipService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FriendshipServiceImpl implements FriendshipService {
    private final FriendshipRepository friendshipRepository;

    @KafkaListener(id = "friendship", topics = {"friendship.create"}, containerFactory = "singleFactory")
    public Friendship createFriendship(String message) {
        var data = new GsonBuilder().create().fromJson(message, HashMap.class);
        Integer idFirstCat = Integer.valueOf((String) data.get("idFirstCat"));
        Integer idSecondCat = Integer.valueOf((String) data.get("idSecondCat"));
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

    @KafkaListener(id = "deleteFriendship", topics = {"friendship.delete"}, containerFactory = "singleFactory")
    public void deleteFriendship(String message) {
        var data = new GsonBuilder().create().fromJson(message, HashMap.class);
        Integer friendshipId = Integer.valueOf((String) data.get("friendshipId"));
        friendshipRepository.deleteById(friendshipId);
    }
}
