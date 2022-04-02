package ru.itmo.service;

import ru.itmo.dao.AbstractDao;
import ru.itmo.models.Owner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OwnerService {
    private AbstractDao abstractDao = new AbstractDao(new Owner());

    public OwnerService() {
    }

    public Owner create(String name, LocalDate date){
        Owner newOwner = new Owner(name, date);
        abstractDao.save(newOwner);
        return newOwner;
    }

    public Owner update(Owner owner) {
        abstractDao.update(owner);
        return owner;
    }

    public Owner delete(Owner owner) {
        abstractDao.delete(owner);
        return owner;
    }

    public Owner findById(int id) {
        return (Owner) abstractDao.findById(id);
    }

    public List<Owner> findAll() {
        List<Object> objects = abstractDao.findAll();
        List<Owner> owners = new ArrayList<Owner>();
        for (Object item : objects) {
            owners.add((Owner) item);
        }
        return owners;
    }
}
