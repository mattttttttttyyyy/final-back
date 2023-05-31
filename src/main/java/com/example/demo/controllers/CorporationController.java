package com.example.demo.controllers;

import com.example.demo.entitys.CorporationEntity;
import com.example.demo.services.CorporationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/corporation")
@CrossOrigin(origins = "http://localhost:4200/")

public class CorporationController {
    @Autowired
    CorporationService service;
    @PostMapping("/add")
    public void addCorporation(@RequestBody CorporationEntity corporation){
        service.createCorporation(corporation);
    }

    @GetMapping("/all")
    public List<CorporationEntity> getAllCorporations(){
        return service.getAllCorporations();
    }

    @GetMapping("/{id}")
    public Optional<CorporationEntity> getCorporationById(@PathVariable long id){
        return service.getCorporationById(id);
    }

}
