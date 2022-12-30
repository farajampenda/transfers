package com.example.transfert.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Comptedepot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double montant;
    private String description;
    private LocalDate date;
    @ManyToOne(fetch = FetchType.LAZY)
    private Compte compte;

    public Comptedepot(Double montant, String description) {
        this.montant = montant;
        this.description = description;
    }

    public Comptedepot() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }
}
