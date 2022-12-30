package com.example.transfert.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String Username;
    private String password;
    private LocalDate date;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> role = new ArrayList<>();
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Compte> compte;

    public User() {
    }

    public User(String nom, String prenom, String username, String password, LocalDate date) {
        this.nom = nom;
        this.prenom = prenom;
        Username = username;
        this.password = password;
        this.date = date;
    }

    public User(String nom, String prenom, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public List<Compte> getCompte() {
        return compte;
    }

    public void setCompte(List<Compte> compte) {
        this.compte = compte;
    }
}
