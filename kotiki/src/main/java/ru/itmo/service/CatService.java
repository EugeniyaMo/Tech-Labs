package ru.itmo.service;

import ru.itmo.dao.AbstractDao;
import ru.itmo.models.Cat;
import ru.itmo.models.Color;
import ru.itmo.models.Owner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CatService {

    private AbstractDao abstractDao = new AbstractDao(new Cat());

    public CatService() {
    }

    public Cat create(String name, LocalDate birthday, Color color, String breed, Owner owner) {
        Cat newCat = new Cat(name, birthday, color, breed);
        owner.addCat(newCat);
        abstractDao.save(newCat);
        return newCat;
    }

    public Cat update(Cat cat) {
        abstractDao.update(cat);
        return cat;
    }

    public Cat delete(Cat cat) {
        abstractDao.delete(cat);
        return cat;
    }

    public Cat findById(int id) {
        return (Cat) abstractDao.findById(id);
    }

    public List<Cat> findAll() {
        List<Object> objects = abstractDao.findAll();
        List<Cat> cats = new ArrayList<Cat>();
        for (Object item : objects) {
            cats.add((Cat) item);
        }
        return cats;
    }
}
