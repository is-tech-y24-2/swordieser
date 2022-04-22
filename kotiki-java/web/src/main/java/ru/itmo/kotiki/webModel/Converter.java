package ru.itmo.kotiki.webModel;

import org.springframework.stereotype.Component;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;

import java.util.ArrayList;
import java.util.List;

@Component
public class Converter {
    public static CatDto catToWebCat(Cat cat) {
        return new CatDto(cat);
    }

    public static Cat webCatToCat(CatDto catDto) {
        return new Cat(catDto.getName(), catDto.getBirthday(), catDto.getBreed(), catDto.getColor());
    }

    public static OwnerDto ownerToWebOwner(Owner owner) {
        return new OwnerDto(owner);
    }

    public static Owner webOwnerToOwner(OwnerDto ownerDto) {
        return new Owner(ownerDto.getName(), ownerDto.getBirthday());
    }

    public static List<CatDto> catsToWebCats(List<Cat> cats) {
        List<CatDto> catDtos = new ArrayList<>();
        for (Cat cat : cats) {
            catDtos.add(Converter.catToWebCat(cat));
        }
        return catDtos;
    }

    public static List<OwnerDto> ownersToWebOwners(List<Owner> owners){
        List<OwnerDto> ownerDtos = new ArrayList<>();
        for (Owner owner : owners) {
            ownerDtos.add(Converter.ownerToWebOwner(owner));
        }
        return ownerDtos;
    }
}
