package ru.itmo.kotiki.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;

import java.util.List;

@Repository
public interface OwnerDao  extends JpaRepository<Owner, Integer>{
}
