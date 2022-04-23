package ru.itmo.kotiki.webModel;


import ru.itmo.kotiki.models.Owner;

import java.sql.Date;


public class OwnerDto {
    private final String name;
    private final Date birthday;

    public OwnerDto(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public OwnerDto(Owner owner) {
        this.name = owner.getName();
        this.birthday = owner.getBirthday();
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }
}
