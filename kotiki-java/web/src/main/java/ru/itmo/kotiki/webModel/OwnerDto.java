package ru.itmo.kotiki.webModel;


import ru.itmo.kotiki.models.Owner;

import java.sql.Date;


public class OwnerDto {
    private final int id;
    private final String name;
    private final Date birthday;


    public OwnerDto(int id, String name, Date birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public OwnerDto(Owner owner) {
        this.id = owner.getId();
        this.name = owner.getName();
        this.birthday = owner.getBirthday();
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

}
