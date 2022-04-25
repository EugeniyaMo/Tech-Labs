package ru.itmo.service;

import ru.itmo.models.Friendship;

import java.util.List;

public interface FriendshipService {
    Friendship createFriendship(int idFirstCat, int idSecondCat);
    Friendship saveFriendship(Friendship friendship);
    Friendship getFriendshipById(int id);
    List<Friendship> getFriendships();
    Friendship deleteFriendship(Friendship friendship);
}
