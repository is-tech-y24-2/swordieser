package ru.itmo.kotiki.webModel;

import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.CatBreed;
import ru.itmo.kotiki.models.CatColor;

import java.sql.Date;

public class WebCat {
    private final String name;
    private final Date birthday;
    private final CatBreed breed;
    private final CatColor color;

    public WebCat(Cat cat){
        this.name = cat.getName();;
        this.birthday = cat.getBirthday();
        this.breed = cat.getBreed();
        this.color = cat.getColor();
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
