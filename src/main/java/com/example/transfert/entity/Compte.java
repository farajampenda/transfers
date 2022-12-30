package com.example.transfert.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String currency;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "compte")
    private List<Comptedepot> depot;


    @OneToMany(mappedBy = "compt", fetch = FetchType.LAZY)
    private List<Caisse> caisse;

    public Compte(String currency, User user) {
        this.currency = currency;
        this.user = user;
    }

    public Compte() {
    }

    public Compte(String currency) {
        this.currency = currency;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Comptedepot> getDepot() {
        return depot;
    }

    public void setDepot(List<Comptedepot> depot) {
        this.depot = depot;
    }

    public List<Caisse> getCaisse() {
        return caisse;
    }

    public void setCaisse(List<Caisse> caisse) {
        this.caisse = caisse;
    }
}
