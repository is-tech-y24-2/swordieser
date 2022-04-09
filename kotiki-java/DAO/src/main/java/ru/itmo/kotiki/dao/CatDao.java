package ru.itmo.kotiki.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface CatDao extends JpaRepository<Cat, Integer> {
}
