package ru.itmo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itmo.dao.AbstractDao;
import ru.itmo.models.Cat;
import ru.itmo.models.Color;
import ru.itmo.models.Owner;
import ru.itmo.repository.ICatRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CatService implements ICatService{
    private final ICatRepository catRepository;

    @Override
    public Cat createCat(String name, String birthday, String breed, int colorId, Owner owner) {
        return catRepository.save(new Cat(name, LocalDate.parse(birthday,
                DateTimeFormatter.ofPattern("yyyy.MM.dd")), breed, colorId, owner));
    }

    @Override
    public Cat saveCat(Cat cat) {
        return catRepository.save(cat);
    }

    @Override
    public Cat getCatById(int id) {
        return catRepository.findById(id);
    }

    @Override
    public List<Cat> getCats() {
        return catRepository.findAll();
    }

    @Override
    public Cat deleteCat(Cat cat) {
        catRepository.delete(cat);
        return cat;
    }
}
