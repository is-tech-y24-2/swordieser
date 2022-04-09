package ru.itmo.kotiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.kotiki.service.OwnerService;
import ru.itmo.kotiki.webModel.Converter;
import ru.itmo.kotiki.webModel.WebOwner;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/findowner/{id}")
    public WebOwner findOwnerById(@PathVariable int id){
        return Converter.ownerToWebOwner(ownerService.findOwner(id));
    }

    @GetMapping("/getallowners")
    public List<WebOwner> getAllOwners(){
        return Converter.ownersToWebOwners(ownerService.findAllOwners());
    }
}
