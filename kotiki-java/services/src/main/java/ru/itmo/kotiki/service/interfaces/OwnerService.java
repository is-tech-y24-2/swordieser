package ru.itmo.kotiki.service.interfaces;

import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;

import java.util.List;


public interface OwnerService {
    Owner findOwner(int id);

    Owner saveOwner(Owner owner);

    void deleteOwner(Owner owner);

    List<Owner> findAllOwners();

    Cat findCatById(int ownerId, int catId);
}
