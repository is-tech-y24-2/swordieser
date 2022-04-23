package ru.itmo.kotiki.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dao.OwnerDao;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;
import ru.itmo.kotiki.service.interfaces.OwnerService;


import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerDao ownerDao;

    @Autowired
    public OwnerServiceImpl(OwnerDao ownerDao){
        this.ownerDao = ownerDao;
    }

    public Owner findOwner(int id) {
        return ownerDao.getById(id);
    }

    public Owner saveOwner(Owner owner) {
        ownerDao.save(owner);
        return owner;
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
