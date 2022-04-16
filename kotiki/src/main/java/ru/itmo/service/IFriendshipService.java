package ru.itmo.service;

import ru.itmo.models.Cat;
import ru.itmo.models.Friendship;
import ru.itmo.models.Owner;

import java.util.List;

public interface IFriendshipService {
    Friendship createFriendship(int idFirstCat, int idSecondCat);
    Friendship saveFriendship(Friendship friendship);
    Friendship getFriendshipById(int id);
    List<Friendship> getFriendships();
    Friendship deleteFriendship(Friendship friendship);
}
