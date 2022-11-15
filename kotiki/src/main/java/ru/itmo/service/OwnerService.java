package ru.itmo.service;

import ru.itmo.models.Owner;

import java.util.List;

public interface OwnerService {
    Owner createOwner(String name, String birthday);
    Owner saveOwner(Owner owner);
    void addCatToOwner(int catId, int ownerId);
    Owner getOwnerById(int ownerId);
    List<Owner> getOwners();
    Owner deleteOwner(Owner owner);
}
