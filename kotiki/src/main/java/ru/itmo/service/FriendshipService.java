package ru.itmo.service;

import ru.itmo.dao.AbstractDao;
import ru.itmo.models.Cat;
import ru.itmo.models.Friendship;
import ru.itmo.models.Owner;

import java.util.ArrayList;
import java.util.List;

public class FriendshipService {
    private AbstractDao abstractDao = new AbstractDao(new Friendship());

    public FriendshipService() {
    }

    public Friendship create(int idFirstCat, int idSecondCat) {
        Friendship newFriendship = new Friendship(idFirstCat, idSecondCat);
        abstractDao.save(newFriendship);
        return newFriendship;
    }

    public Friendship update(Friendship friendship) {
        abstractDao.update(friendship);
        return friendship;
    }

    public Friendship delete(Friendship friendship) {
        abstractDao.delete(friendship);
        return friendship;
    }

    public Friendship findById(int id) {
        return (Friendship) abstractDao.findById(id);
    }

    public List<Friendship> findAll() {
        List<Object> objects = abstractDao.findAll();
        List<Friendship> friendships = new ArrayList<Friendship>();
        for (Object item : objects) {
            friendships.add((Friendship) item);
        }
        return friendships;
    }
}
