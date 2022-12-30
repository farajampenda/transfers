package com.example.transfert.controller;

import com.example.transfert.dto.CompteDto;
import com.example.transfert.repository.CompteRepository;
import com.example.transfert.service.Compteservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompteController {
    @Autowired
    private Compteservice service;
    @Autowired
    private CompteRepository c;

    @GetMapping("/compte/{userId}")
    public ResponseEntity<?> selectCompte(@PathVariable("userId") Long userId) {
        return service.monCompte(userId);
    }

    @PostMapping("compte/add/{id}")
    public ResponseEntity<?> addCompte(@RequestBody CompteDto compte, @PathVariable("id") Long id) {
        return service.addCompte(compte, id);
    }

    @GetMapping("/solde/{compteID}")
    public ResponseEntity<?> selectSolde(@PathVariable("compteID") Long compteID) {
        return service.monSolde(compteID);
    }

}
