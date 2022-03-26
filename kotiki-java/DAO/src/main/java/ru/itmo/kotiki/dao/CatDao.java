package ru.itmo.kotiki.dao;

import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;

import java.util.List;

public interface CatDao {
    public Cat findById(int id);
    public void save(Cat cat);
    public void update(Cat cat);
    public void delete(Cat cat);
    public Owner findOwnerById(int id);
    public List<Cat> findAll();
}
