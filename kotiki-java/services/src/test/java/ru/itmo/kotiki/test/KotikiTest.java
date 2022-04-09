package ru.itmo.kotiki.test;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.CatBreed;
import ru.itmo.kotiki.models.CatColor;
import ru.itmo.kotiki.models.Owner;
import ru.itmo.kotiki.service.OwnerService;


import java.sql.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class KotikiTest {
    private Session session;
    private OwnerService ownerService;


    @Before
    public void setup(){
        session = mock(Session.class);
        ownerService = mock(OwnerService.class);
    }

    @Test
    public void addOwnerTest(){
        Owner owner = new Owner("Ben", Date.valueOf("2000-01-01"));
        ownerService.saveOwner(owner);
        when(ownerService.findOwner(1)).thenReturn(owner);
        assertEquals("Ben", ownerService.findOwner(1).getName());
    }

    @Test
    public void findByIdTest(){
        Owner owner = new Owner("Serj", Date.valueOf("2002-01-01"));
        ownerService.saveOwner(owner);
        when(ownerService.findOwner(1)).thenReturn(owner);
        assertEquals(owner, ownerService.findOwner(1));
    }

    @Test
    public void findCatByIdTest(){
        Owner owner = new Owner("Ben", Date.valueOf("2000-01-01"));
        ownerService.saveOwner(owner);
        Cat cat = new Cat("Kisik", Date.valueOf("2010-01-01"), CatBreed.MAINE_COON, CatColor.MIXED);
        owner.addCat(cat);
        ownerService.saveOwner(owner);
        when(ownerService.findCatById(1,1)).thenReturn(cat);
        assertEquals(cat, ownerService.findCatById(1,1));
    }
}
