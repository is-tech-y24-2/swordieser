package ru.itmo.kotiki.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.models.Owner;

@Repository
public interface OwnerDao extends JpaRepository<Owner, Integer> {
}
