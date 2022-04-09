package ru.itmo.kotiki.webModel;


import ru.itmo.kotiki.models.Owner;

import java.sql.Date;

public class WebOwner {
    private String name;
    private Date birthday;

    public WebOwner(){
    }

    public WebOwner(Owner owner){
        this.name = owner.getName();;
        this.birthday = owner.getBirthday();
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }
}
