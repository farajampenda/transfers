package com.example.transfert.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

public class ComptedepotDto implements Serializable {
    private Long depotId;
    private Double montant;
    private String descriptions;
    private LocalDate date;
    private CompteDto comptes;
    private int compteid;

    public ComptedepotDto(Double montant, String descriptions) {
        this.montant = montant;
        this.descriptions = descriptions;
    }

    public ComptedepotDto(Double montant, int compteid) {
        this.montant = montant;
        this.compteid = compteid;
    }

    public ComptedepotDto() {
    }

    public Long getDepotId() {
        return depotId;
    }

    public void setDepotId(Long depotId) {
        this.depotId = depotId;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public CompteDto getComptes() {
        return comptes;
    }

    public void setComptes(CompteDto comptes) {
        this.comptes = comptes;
    }

    public int getCompteid() {
        return compteid;
    }

    public void setCompteid(int compteid) {
        this.compteid = compteid;
    }
}
