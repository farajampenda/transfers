package com.example.transfert.dto;


import com.example.transfert.entity.Retrait;

import java.time.LocalDate;


public class RetraitDto {
    private Long id;
    private int tva;
    private Double montantretrait;
    private Double montantcaisse;
    private String codeDetransation;
    private LocalDate date;

    public RetraitDto() {

    }

    public RetraitDto(int tva, Double montantretrait, Double montantcaisse, String codeDetransation, LocalDate date) {
        this.tva = tva;
        this.montantretrait = montantretrait;
        this.montantcaisse = montantcaisse;
        this.codeDetransation = codeDetransation;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTva() {
        return tva;
    }

    public void setTva(int tva) {
        this.tva = tva;
    }

    public Double getMontantretrait() {
        return montantretrait;
    }

    public void setMontantretrait(Double montantretrait) {
        this.montantretrait = montantretrait;
    }

    public Double getMontantcaisse() {
        return montantcaisse;
    }

    public void setMontantcaisse(Double montantcaisse) {
        this.montantcaisse = montantcaisse;
    }

    public String getCodeDetransation() {
        return codeDetransation;
    }

    public void setCodeDetransation(String codeDetransation) {
        this.codeDetransation = codeDetransation;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


}
