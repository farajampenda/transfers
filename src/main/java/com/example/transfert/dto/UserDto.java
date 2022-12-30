package com.example.transfert.dto;

import com.example.transfert.entity.Compte;

import java.time.LocalDate;
import java.util.List;

public class UserDto {
    private Long id;
    private String name;
    private String lastname;
    private String users;
    private String code;
    private LocalDate date;
    private List<RoleDto> role;
    private List<Compte> compte;

    public UserDto() {
    }

    public UserDto(Long id, String name, String lastname, String users, String code, LocalDate date, List<RoleDto> role) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.users = users;
        this.code = code;
        this.date = date;
        this.role = role;
    }

    public UserDto(String name, String lastname, String code) {
        this.name = name;
        this.lastname = lastname;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<RoleDto> getRole() {
        return role;
    }

    public void setRole(List<RoleDto> role) {
        this.role = role;
    }

    public List<Compte> getCompte() {
        return compte;
    }

    public void setCompte(List<Compte> compte) {
        this.compte = compte;
    }
}
