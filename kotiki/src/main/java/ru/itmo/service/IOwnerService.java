package ru.itmo.service;

import ru.itmo.models.Cat;
import ru.itmo.models.Owner;

import java.time.LocalDate;
import java.util.List;

public interface IOwnerService {
    Owner createOwner(String name, String birthday);
    Owner saveOwner(Owner owner);
    void addCatToOwner(int catId, int ownerId);
    Owner getOwnerById(int ownerId);
    List<Owner> getOwners();
    Owner deleteOwner(Owner owner);
}
