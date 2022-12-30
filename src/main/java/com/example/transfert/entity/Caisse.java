package com.example.transfert.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Caisse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String receveur;
    private Double montant;
    private int tva;
    private Double fraistransfert;
    private String codetansaction;
    private Boolean validation;
    private LocalDate date;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Compte compt;

    @OneToMany(mappedBy = "caisse")
    private List<Retrait> retrait;

    public Caisse() {
    }

    public Caisse(String receveur, Double montant) {
        this.receveur = receveur;
        this.montant = montant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceveur() {
        return receveur;
    }

    public void setReceveur(String receveur) {
        this.receveur = receveur;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public int getTva() {
        return tva;
    }

    public void setTva(int tva) {
        this.tva = tva;
    }

    public Double getFraistransfert() {
        return fraistransfert;
    }

    public void setFraistransfert(Double fraistransfert) {
        this.fraistransfert = fraistransfert;
    }

    public String getCodetansaction() {
        return codetansaction;
    }

    public void setCodetansaction(String codetansaction) {
        this.codetansaction = codetansaction;
    }

    public Boolean getValidation() {
        return validation;
    }

    public void setValidation(Boolean validation) {
        this.validation = validation;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Compte getCompt() {
        return compt;
    }

    public void setCompt(Compte compt) {
        this.compt = compt;
    }
}
