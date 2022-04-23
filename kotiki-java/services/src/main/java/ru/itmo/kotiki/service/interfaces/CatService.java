package ru.itmo.kotiki.service.interfaces;

import ru.itmo.kotiki.models.Cat;

import java.util.List;


public interface CatService {
    Cat findCat(int id);

    Cat saveCat(Cat cat);

    void deleteCat(Cat cat);

    List<Cat> findAllCats();
}
