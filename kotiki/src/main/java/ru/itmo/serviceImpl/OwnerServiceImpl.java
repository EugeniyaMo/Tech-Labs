package ru.itmo.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itmo.models.Cat;
import ru.itmo.models.Owner;
import ru.itmo.repository.CatRepository;
import ru.itmo.repository.OwnerRepository;
import ru.itmo.service.OwnerService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final CatRepository catRepository;

    @Override
    public Owner createOwner(String name, String birthday) {
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

    @Override
    public Owner deleteOwner(Owner owner) {
        ownerRepository.delete(owner);
        return owner;
    }
}
