package ru.itmo.kotiki.webModel;


import ru.itmo.kotiki.models.Owner;

import java.sql.Date;

public class WebOwner {
    private final String name;
    private final Date birthday;

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
