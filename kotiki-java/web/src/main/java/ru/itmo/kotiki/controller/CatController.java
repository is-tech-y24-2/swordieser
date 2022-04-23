package ru.itmo.kotiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.service.implementation.CatServiceImpl;
import ru.itmo.kotiki.webModel.CatDto;
import ru.itmo.kotiki.webModel.Converter;

import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatController {
    private final CatServiceImpl catServiceImpl;

    @Autowired
    public CatController(CatServiceImpl catServiceImpl) {
        this.catServiceImpl = catServiceImpl;
    }

    @PostMapping("/create")
    public CatDto createCat(@RequestBody CatDto catDto) {
        return Converter.catToWebCat(catServiceImpl.saveCat(Converter.webCatToCat(catDto)));
    }

    @GetMapping("/{id}")
    public CatDto findCatById(@PathVariable int id) {
        return Converter.catToWebCat(catServiceImpl.findCat(id));
    }

    @GetMapping("/all")
    public List<CatDto> getAllCats() {
        return Converter.catsToWebCats(catServiceImpl.findAllCats());
    }

    @PutMapping("/{id}")
    public void updateCat(@PathVariable int id, String name) {
        Cat cat = catServiceImpl.findCat(id);
        cat.setName(name);
        catServiceImpl.saveCat(cat);
    }

    @DeleteMapping("/{id}")
    public void deleteCatById(@PathVariable int id) {
        catServiceImpl.deleteCat(catServiceImpl.findCat(id));
    }
}
