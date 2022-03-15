package ru.itmo.kotiki;

import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.CatBreed;
import ru.itmo.kotiki.models.CatColor;
import ru.itmo.kotiki.models.Owner;
import ru.itmo.kotiki.service.OwnerService;
import java.sql.Date;

import java.time.LocalDate;


public class Program {
    public static void main(String[] args) {
        OwnerService userService = new OwnerService();
        Owner user = new Owner("owner", Date.valueOf(LocalDate.of(2000, 1, 1)));
        userService.saveOwner(user);
        Cat cat = new Cat("cat", Date.valueOf(LocalDate.of(2002, 1, 1)), CatBreed.Persian, CatColor.White);
        user.addCat(cat);
        userService.updateOwner(user);
    }
}
