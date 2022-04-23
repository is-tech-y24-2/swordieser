package ru.itmo.kotiki.webModel;


import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.CatBreed;
import ru.itmo.kotiki.models.CatColor;

import java.sql.Date;


public class CatDto {
    private final int id;
    private final String name;
    private final Date birthday;
    private final CatBreed breed;
    private final CatColor color;

    public CatDto(int id, String name, Date birthday, CatBreed breed, CatColor color) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.breed = breed;
        this.color = color;
    }

    public CatDto(Cat cat) {
        this.id = cat.getId();
        this.name = cat.getName();
        this.birthday = cat.getBirthday();
        this.breed = cat.getBreed();
        this.color = cat.getColor();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public CatBreed getBreed() {
        return breed;
    }

    public CatColor getColor() {
        return color;
    }
}
