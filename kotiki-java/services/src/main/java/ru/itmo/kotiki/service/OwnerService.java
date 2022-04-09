package ru.itmo.kotiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dao.OwnerDao;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;


import java.util.List;

@Service
public class OwnerService {
    private final OwnerDao ownerDao;

    @Autowired
    public OwnerService(OwnerDao ownerDao){
        this.ownerDao = ownerDao;
    }

    public Owner findOwner(int id) {
        return ownerDao.getById(id);
    }

    public void saveOwner(Owner owner) {
        ownerDao.save(owner);
    }

    public void deleteOwner(Owner owner) {
        ownerDao.delete(owner);
    }

    public List<Owner> findAllOwners() {
        return ownerDao.findAll();
    }

    public Cat findCatById(int ownerId, int catId) {
        for (Cat cat : findOwner(ownerId).getCats()) {
            if (catId == cat.getId()){
                return cat;
            }
        }
        return null;
    }
}
