package com.example.transfert.dto;

import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.time.LocalDate;

public class CaisseDto implements Serializable {
    private Long id;
    private String nomRecevuer;
    private Double somme;
    private int tva;
    private Double fraistransfert;
    private String codetansaction;
    private String validation;
    private LocalDate date;
    private CompteDto compt;
    private int compte;

    public CaisseDto() {
    }

    public CaisseDto(String nomRecevuer, Double somme, int compte) {
        this.nomRecevuer = nomRecevuer;
        this.somme = somme;
        this.compte = compte;
    }

    public int getCompte() {
        return compte;
    }

    public void setCompte(int compte) {
        this.compte = compte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomRecevuer() {
        return nomRecevuer;
    }

    public void setNomRecevuer(String nomRecevuer) {
        this.nomRecevuer = nomRecevuer;
    }

    public Double getSomme() {
        return somme;
    }

    public void setSomme(Double somme) {
        this.somme = somme;
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

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public CompteDto getCompt() {
        return compt;
    }

    public void setCompt(CompteDto compt) {
        this.compt = compt;
    }

}
