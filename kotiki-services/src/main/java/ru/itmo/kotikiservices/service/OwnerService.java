package ru.itmo.kotikiservices.service;

import ru.itmo.kotikiservices.models.Owner;

import java.util.List;

public interface OwnerService {
    Owner createOwner(String message);
    Owner saveOwner(Owner owner);
    void addCatToOwner(int catId, int ownerId);
    Owner getOwnerById(int ownerId);
    List<Owner> getOwners();
    void deleteOwner(String message);
}
