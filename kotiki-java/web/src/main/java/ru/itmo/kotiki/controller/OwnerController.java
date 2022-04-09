package ru.itmo.kotiki.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.models.Owner;
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

    @PostMapping("/create")
    public void createOwner(@RequestBody WebOwner webOwner){
        ownerService.saveOwner(Converter.webOwnerToOwner(webOwner));
    }

    @GetMapping("/findowner/{id}")
    public WebOwner findOwnerById(@PathVariable int id){
        return Converter.ownerToWebOwner(ownerService.findOwner(id));
    }

    @GetMapping("/getallowners")
    public List<WebOwner> getAllOwners(){
        return Converter.ownersToWebOwners(ownerService.findAllOwners());
    }

    @PutMapping("/update/{id}")
    public void updateOwner(@PathVariable int id, String name){
        Owner owner = ownerService.findOwner(id);
        owner.setName(name);
        ownerService.saveOwner(owner);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOwner(@PathVariable int id){
        ownerService.deleteOwner(ownerService.findOwner(id));
    }
}
