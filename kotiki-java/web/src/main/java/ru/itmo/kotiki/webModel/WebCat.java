package ru.itmo.kotiki.webModel;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.CatBreed;
import ru.itmo.kotiki.models.CatColor;

import java.sql.Date;

@Component
public class WebCat {
    private String name;
    private Date birthday;
    private CatBreed breed;
    private CatColor color;

    @Autowired
    public WebCat(){
    }

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
