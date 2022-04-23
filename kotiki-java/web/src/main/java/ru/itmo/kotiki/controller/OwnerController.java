package ru.itmo.kotiki.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.models.Owner;
import ru.itmo.kotiki.service.implementation.OwnerServiceImpl;
import ru.itmo.kotiki.webModel.Converter;
import ru.itmo.kotiki.webModel.OwnerDto;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerServiceImpl ownerServiceImpl;

    @Autowired
    public OwnerController(OwnerServiceImpl ownerServiceImpl) {
        this.ownerServiceImpl = ownerServiceImpl;
    }

    @PostMapping("/create")
    public OwnerDto createOwner(@RequestBody OwnerDto ownerDto){
        return Converter.ownerToWebOwner(ownerServiceImpl.saveOwner(Converter.webOwnerToOwner(ownerDto)));
    }

    @GetMapping("/{id}")
    public OwnerDto findOwnerById(@PathVariable int id){
        return Converter.ownerToWebOwner(ownerServiceImpl.findOwner(id));
    }

    @GetMapping("/all")
    public List<OwnerDto> getAllOwners(){
        return Converter.ownersToWebOwners(ownerServiceImpl.findAllOwners());
    }

    @PutMapping("/{id}")
    public void updateOwner(@PathVariable int id, String name){
        Owner owner = ownerServiceImpl.findOwner(id);
        owner.setName(name);
        ownerServiceImpl.saveOwner(owner);
    }

    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable int id){
        ownerServiceImpl.deleteOwner(ownerServiceImpl.findOwner(id));
    }
}
