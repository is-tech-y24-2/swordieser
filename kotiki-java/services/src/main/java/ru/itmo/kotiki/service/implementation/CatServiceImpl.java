package ru.itmo.kotiki.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dao.CatDao;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.service.interfaces.CatService;

import java.util.List;

@Service
public class CatServiceImpl implements CatService {
    private final CatDao catDao;

    @Autowired
    public CatServiceImpl(CatDao catDao) {
        this.catDao = catDao;
    }

    public Cat findCat(int id) {
        return catDao.getById(id);
    }

    public Cat saveCat(Cat cat) {
        catDao.save(cat);
        return cat;
    }

    public void deleteCat(Cat cat) {
        catDao.delete(cat);
    }

    public List<Cat> findAllCats() {
        return catDao.findAll();
    }
}

