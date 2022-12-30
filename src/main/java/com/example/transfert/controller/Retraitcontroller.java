package com.example.transfert.controller;

import com.example.transfert.dto.RetraitDto;
import com.example.transfert.service.Retraitservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Retraitcontroller {

    @Autowired
    private Retraitservice service;

    @PostMapping("/retrait/add/{agentID}")
    private ResponseEntity<?> retrait(@PathVariable("agentID") Long id, @RequestBody RetraitDto dto) {
        return service.retrait(id, dto);
    }

    @GetMapping("/retrait/list/{agentID}")
    private ResponseEntity<?> list(@PathVariable("agentID") Long agentID) {
        return service.listRetrait(agentID);
    }
}
