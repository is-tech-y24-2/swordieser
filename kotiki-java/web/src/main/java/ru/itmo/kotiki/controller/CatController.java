package ru.itmo.kotiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;
import ru.itmo.kotiki.service.implementation.CatServiceImpl;
import ru.itmo.kotiki.service.implementation.OwnerServiceImpl;
import ru.itmo.kotiki.webModel.CatDto;
import ru.itmo.kotiki.webModel.Converter;

import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatController {
    private final CatServiceImpl catServiceImpl;
    private final OwnerServiceImpl ownerServiceImpl;

    @Autowired
    public CatController(CatServiceImpl catServiceImpl, OwnerServiceImpl ownerServiceImpl) {
        this.catServiceImpl = catServiceImpl;
        this.ownerServiceImpl = ownerServiceImpl;
    }

    @PostMapping("/create")
    public CatDto createCat(@RequestBody CatDto catDto) {
        return Converter.catToWebCat(catServiceImpl.saveCat(Converter.webCatToCat(catDto)));
    }

    @GetMapping("/{id}")
    public CatDto findCatById(@PathVariable int id) {
        Cat cat = catServiceImpl.findCat(id);
        return Converter.catToWebCat(cat);
    }

    @GetMapping("/all")
    public List<CatDto> getAllCats() {
        return Converter.catsToWebCats(catServiceImpl.findAllCats());
    }


    @PutMapping("/put/{id}")
    public void updateCat(@PathVariable int id, int ownerId) {
        Cat cat = catServiceImpl.findCat(id);
        if (cat != null) {
            Owner owner = ownerServiceImpl.findOwner(ownerId);
            cat.setOwner(owner);
            catServiceImpl.saveCat(cat);
        }
    }

    @DeleteMapping("/del/{id}")
    public void deleteCatById(@PathVariable int id) {
        catServiceImpl.deleteCat(catServiceImpl.findCat(id));
    }
}
