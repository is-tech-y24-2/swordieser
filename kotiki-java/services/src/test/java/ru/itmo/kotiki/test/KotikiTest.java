package ru.itmo.kotiki.test;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.CatBreed;
import ru.itmo.kotiki.models.CatColor;
import ru.itmo.kotiki.models.Owner;
import ru.itmo.kotiki.service.implementation.OwnerServiceImpl;


import java.sql.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class KotikiTest {
    private Session session;
    private OwnerServiceImpl ownerServiceImpl;


    @Before
    public void setup(){
        session = mock(Session.class);
        ownerServiceImpl = mock(OwnerServiceImpl.class);
    }

    @Test
    public void addOwnerTest(){
        Owner owner = new Owner("Ben", Date.valueOf("2000-01-01"));
        ownerServiceImpl.saveOwner(owner);
        when(ownerServiceImpl.findOwner(1)).thenReturn(owner);
        assertEquals("Ben", ownerServiceImpl.findOwner(1).getName());
    }

    @Test
    public void findByIdTest(){
        Owner owner = new Owner("Serj", Date.valueOf("2002-01-01"));
        ownerServiceImpl.saveOwner(owner);
        when(ownerServiceImpl.findOwner(1)).thenReturn(owner);
        assertEquals(owner, ownerServiceImpl.findOwner(1));
    }

    @Test
    public void findCatByIdTest(){
        Owner owner = new Owner("Ben", Date.valueOf("2000-01-01"));
        ownerServiceImpl.saveOwner(owner);
        Cat cat = new Cat("Kisik", Date.valueOf("2010-01-01"), CatBreed.MAINE_COON, CatColor.MIXED);
        owner.addCat(cat);
        ownerServiceImpl.saveOwner(owner);
        when(ownerServiceImpl.findCatById(1,1)).thenReturn(cat);
        assertEquals(cat, ownerServiceImpl.findCatById(1,1));
    }
}
