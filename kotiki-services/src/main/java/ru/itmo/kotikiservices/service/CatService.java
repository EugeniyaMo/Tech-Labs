package ru.itmo.kotikiservices.service;

import ru.itmo.kotikiservices.models.Cat;
import ru.itmo.kotikiservices.models.Owner;

import java.util.List;

public interface CatService {
    Cat createCat(String message);
    Cat saveCat(Cat cat);
    Cat getCatById(int id);
    List<Cat> getCats();
    void deleteCat(String message);
}
