package ru.itmo.kotikiservices.service;

import ru.itmo.kotikiservices.models.Friendship;

import java.util.List;

public interface FriendshipService {
    Friendship createFriendship(String message);
    Friendship saveFriendship(Friendship friendship);
    Friendship getFriendshipById(int id);
    List<Friendship> getFriendships();
    void deleteFriendship(String message);
}
