package com.example.transfert.controller;

import com.example.transfert.dto.CaisseDto;
import com.example.transfert.service.Servicecaisse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CaisseController {
    @Autowired
    private Servicecaisse service;


    @PostMapping("/transfert/add")
    public ResponseEntity<?> transfert(@RequestBody CaisseDto caisse) {
        return service.transfert(caisse);
    }

    @DeleteMapping("/transfert/delete/{caisseid}")
    public ResponseEntity<?> annule(@PathVariable("caisseid") Long caisseid) {
        return service.deleteService(caisseid);
    }

    @GetMapping("/transfert/select/{compteid}")
    public ResponseEntity<?> select(@PathVariable("compteid") Long compteid) {
        return service.select(compteid);
    }

}
