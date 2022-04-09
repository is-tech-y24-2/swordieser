package ru.itmo.kotiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dao.CatDao;
import ru.itmo.kotiki.models.Cat;


import java.util.List;

@Service
public class CatService {
    private final CatDao catDao;
    
    @Autowired
    public CatService(CatDao catDao) {
        this.catDao = catDao;
    }

    public Cat findCat(int id) {
        return catDao.getById(id);
    }

    public void saveCat(Cat cat) {
        catDao.save(cat);
    }

    public void deleteCat(Cat cat) {
        catDao.delete(cat);
    }

    public List<Cat> findAllCats() {
        return catDao.findAll();
    }
}

