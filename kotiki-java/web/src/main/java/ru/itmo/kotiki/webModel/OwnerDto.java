package ru.itmo.kotiki.webModel;


import org.springframework.beans.factory.annotation.Autowired;
import ru.itmo.kotiki.models.Owner;

import java.sql.Date;


public class OwnerDto {
    private String name;
    private Date birthday;

    @Autowired
    public OwnerDto(){
    }

    public OwnerDto(Owner owner){
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
