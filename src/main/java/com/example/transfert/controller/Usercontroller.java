package com.example.transfert.controller;

import com.example.transfert.dto.UserDto;
import com.example.transfert.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Usercontroller {

    @Autowired
    private Userservice service;

    @GetMapping("/user/{id}")
    public ResponseEntity<?> allUsers(@PathVariable("id") Long id) {
        return service.selectUsers(id);
    }

    @PostMapping("/user/add")
    private ResponseEntity<?> addUser(@RequestBody UserDto user) {
        return service.addUser(user);
    }

    @PutMapping("/user/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserDto user) {
        return service.updateUser(id, user);
    }

    @GetMapping("/user/del/{id}")
    public ResponseEntity<?> delleteUser(@PathVariable("id") Long id) {
        return service.deleteUser(id);
    }
}
