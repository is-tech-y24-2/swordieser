package ru.itmo.kotiki.webModel;

import org.springframework.stereotype.Component;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;

import java.util.ArrayList;
import java.util.List;

@Component
public class Converter {
    public static WebCat catToWebCat(Cat cat) {
        return new WebCat(cat);
    }

    public static Cat webCatToCat(WebCat webCat) {
        return new Cat(webCat.getName(), webCat.getBirthday(), webCat.getBreed(), webCat.getColor());
    }

    public static WebOwner ownerToWebOwner(Owner owner) {
        return new WebOwner(owner);
    }

    public static Owner webOwnerToOwner(WebOwner webOwner) {
        return new Owner(webOwner.getName(), webOwner.getBirthday());
    }

    public static List<WebCat> catsToWebCats(List<Cat> cats) {
        List<WebCat> webCats = new ArrayList<>();
        for (Cat cat : cats) {
            webCats.add(Converter.catToWebCat(cat));
        }
        return webCats;
    }

    public static List<WebOwner> ownersToWebOwners(List<Owner> owners){
        List<WebOwner> webOwners = new ArrayList<>();
        for (Owner owner : owners) {
            webOwners.add(Converter.ownerToWebOwner(owner));
        }
        return webOwners;
    }
}
