package ru.itmo.kotiki.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.models.Cat;

@Repository
public interface CatDao extends JpaRepository<Cat, Integer> {
    @Override
    Cat getById(Integer id);
}
