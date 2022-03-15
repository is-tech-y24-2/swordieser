package ru.itmo.kotiki.service;

import ru.itmo.kotiki.dao.implementation.OwnerDaoImpl;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;

import java.util.List;

public class OwnerService {
    private OwnerDaoImpl ownerDao = new OwnerDaoImpl();

    public OwnerService(){}

    public Owner findOwner(int id) {
        return ownerDao.findById(id);
    }

    public void saveOwner(Owner owner) {
        ownerDao.save(owner);
    }

    public void deleteOwner(Owner owner) {
        ownerDao.delete(owner);
    }

    public void updateOwner(Owner owner) {
        ownerDao.update(owner);
    }

    public List<Owner> findAllOwners() {
        return ownerDao.findAll();
    }

    public Cat findAutoById(int id) {
        return ownerDao.findCatById(id);
    }
}
