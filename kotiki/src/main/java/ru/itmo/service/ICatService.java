package ru.itmo.service;

import ru.itmo.models.Cat;
import ru.itmo.models.Owner;

import java.util.List;

public interface ICatService {
    Cat createCat(String name, String birthday, String breed, int colorId, Owner owner);
    Cat saveCat(Cat cat);
    Cat getCatById(int id);
    List<Cat> getCats();
    Cat deleteCat(Cat cat);
}
