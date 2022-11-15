package ru.itmo.kotikiservices.serviceImpl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.itmo.kotikiservices.models.Cat;
import ru.itmo.kotikiservices.models.Owner;
import ru.itmo.kotikiservices.repository.CatRepository;
import ru.itmo.kotikiservices.service.CatService;
import ru.itmo.kotikiservices.service.OwnerService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CatServiceImpl implements CatService {
    private final CatRepository catRepository;
    private final OwnerService ownerService;

    @Override
    @KafkaListener(id = "createCat", topics = {"cat.create"}, containerFactory = "singleFactory")
    public Cat createCat(String message) {
        var data = new GsonBuilder().create().fromJson(message, HashMap.class);
        String name = data.get("name").toString();
        String birthday = data.get("birthday").toString();
        String breed = data.get("breed").toString();
        Integer colorId = Integer.valueOf((String) data.get("colorId"));
        Integer ownerId = Integer.valueOf((String) data.get("ownerId"));
        Owner owner = ownerService.getOwnerById(ownerId);
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

    @KafkaListener(id = "deleteCat", topics = {"cat.delete"}, containerFactory = "singleFactory")
    public void deleteCat(String message) {
        var data = new GsonBuilder().create().fromJson(message, HashMap.class);
        Integer catId = Integer.valueOf((String) data.get("catId"));
        catRepository.deleteById(catId);
    }
}
