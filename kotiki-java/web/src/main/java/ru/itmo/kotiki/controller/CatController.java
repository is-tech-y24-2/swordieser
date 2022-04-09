package ru.itmo.kotiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/findcat/{id}")
    public WebCat findCatById(@PathVariable int id){
        return Converter.catToWebCat(catService.findCat(id));
    }

    @GetMapping("/getallcats")
    public List<WebCat> getAllCats(){
        return Converter.catsToWebCats(catService.findAllCats());
    }
}
