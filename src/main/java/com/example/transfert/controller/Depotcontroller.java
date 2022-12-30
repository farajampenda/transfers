package com.example.transfert.controller;

import com.example.transfert.dto.ComptedepotDto;
import com.example.transfert.service.Depotservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Depotcontroller {
    @Autowired
    private Depotservice service;

    @PostMapping("/depot/add")
    public ResponseEntity<?> Depot(@RequestBody ComptedepotDto depos) {
        return service.depot(depos);
    }

    @GetMapping("/depot/list/{compteId}")
    public ResponseEntity<?> description(@PathVariable("compteId") Long compteId) {
        return service.description(compteId);
    }
}
