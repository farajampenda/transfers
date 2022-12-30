package com.example.transfert.controller;

import com.example.transfert.dto.AgentDto;
import com.example.transfert.service.Agentservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Agentcontroller {

    @Autowired
    private Agentservice service;

    @PostMapping("/agent/add")
    public ResponseEntity<?> add(@RequestBody AgentDto agentDto) {
        return service.add(agentDto);
    }

    @GetMapping("/agent/list")
    public ResponseEntity<?> allAgent() {
        return service.allAgent();
    }

    @GetMapping("agent/get/{agnetID}")
    public ResponseEntity<?> agentOne(@PathVariable("agnetID") Long agnetID) {
        return service.agentOne(agnetID);
    }

    @DeleteMapping("agent/del/{agnetID}")
    public ResponseEntity<?> delete(@PathVariable("agnetID") Long agnetID) {
        return service.delete(agnetID);
    }

    @PutMapping("agent/put/{agnetID}")
    public ResponseEntity<?> update(@PathVariable("agnetID") Long agnetID, @RequestBody AgentDto agentDto) {
        return service.update(agnetID, agentDto);
    }
}
