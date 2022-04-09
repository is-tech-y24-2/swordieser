package ru.itmo.kotiki.webModel;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itmo.kotiki.models.Owner;

import java.sql.Date;

@Component
public class WebOwner {
    private String name;
    private Date birthday;

    @Autowired
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
