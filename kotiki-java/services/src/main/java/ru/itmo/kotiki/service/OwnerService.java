package ru.itmo.kotiki.service;

import ru.itmo.kotiki.dao.implementation.OwnerDaoImpl;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;

import java.util.List;

public class OwnerService {
    private OwnerDaoImpl ownerDao = new OwnerDaoImpl();

    public OwnerService(){}

    public Owner findUser(int id) {
        return ownerDao.findById(id);
    }

    public void saveUser(Owner user) {
        ownerDao.save(user);
    }

    public void deleteUser(Owner user) {
        ownerDao.delete(user);
    }

    public void updateUser(Owner user) {
        ownerDao.update(user);
    }

    public List<Owner> findAllUsers() {
        return ownerDao.findAll();
    }

    public Cat findAutoById(int id) {
        return ownerDao.findCatById(id);
    }
}
