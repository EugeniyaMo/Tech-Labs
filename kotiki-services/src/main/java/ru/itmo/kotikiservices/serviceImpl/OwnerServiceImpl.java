package ru.itmo.kotikiservices.serviceImpl;

import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.itmo.kotikiservices.models.Cat;
import ru.itmo.kotikiservices.models.Owner;
import ru.itmo.kotikiservices.repository.CatRepository;
import ru.itmo.kotikiservices.repository.OwnerRepository;
import ru.itmo.kotikiservices.service.OwnerService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final CatRepository catRepository;

    @KafkaListener(id = "createOwner", topics = {"owner.create"}, containerFactory = "singleFactory")
    public Owner createOwner(String message) {
        var data = new GsonBuilder().create().fromJson(message, HashMap.class);
        String name = data.get("name").toString();
        String birthday = data.get("birthday").toString();
        return ownerRepository.save(new Owner(name, LocalDate.parse(birthday,
                DateTimeFormatter.ofPattern("yyyy.MM.dd"))));
    }

    @Override
    public Owner saveOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public void addCatToOwner(int catId, int ownerId) {
        Owner owner = ownerRepository.findById(ownerId);
        Cat cat = catRepository.findById(catId);
        cat.setOwner(owner);
        owner.getCats().add(cat);
    }

    @Override
    public Owner getOwnerById(int ownerId) {
        return ownerRepository.findById(ownerId);
    }

    @Override
    public List<Owner> getOwners() {
        return ownerRepository.findAll();
    }

    @KafkaListener(id = "deleteOwner", topics = {"owner.delete"}, containerFactory = "singleFactory")
    public void deleteOwner(String message) {
        var data = new GsonBuilder().create().fromJson(message, HashMap.class);
        Integer ownerId = Integer.valueOf((String) data.get("ownerId"));
        ownerRepository.deleteById(ownerId);
    }
}
