package ru.itmo.kotiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.service.CatService;
import ru.itmo.kotiki.webModel.Converter;
import ru.itmo.kotiki.webModel.WebCat;

import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatController {
    private final CatService catService;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @PostMapping("/create")
    public void createCat(@RequestBody WebCat webCat){
        catService.saveCat(Converter.webCatToCat(webCat));
    }

    @GetMapping("/findcat/{id}")
    public WebCat findCatById(@PathVariable int id){
        return Converter.catToWebCat(catService.findCat(id));
    }

    @GetMapping("/getallcats")
    public List<WebCat> getAllCats(){
        return Converter.catsToWebCats(catService.findAllCats());
    }

    @PutMapping("/update/{id}")
    public void updateCat(@PathVariable int id, String name){
        Cat cat = catService.findCat(id);
        cat.setName(name);
        catService.saveCat(cat);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCatById(@PathVariable int id){
        catService.deleteCat(catService.findCat(id));
    }
}
