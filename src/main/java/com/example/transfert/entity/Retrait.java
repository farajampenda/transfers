package com.example.transfert.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Retrait {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int tva;
    private Double montantretrait;
    private Double montantcaisse;
    private String codetransaction;
    @JsonIgnore
    @ManyToOne
    private Agent agent;
    @JsonIgnore
    @ManyToOne
    private Caisse caisse;
    private LocalDate date;

    public Retrait() {
    }

    public Retrait(String codetransaction) {
        this.codetransaction = codetransaction;
    }

    public Retrait(int tva, Double montantretrait, Double montantcaisse, Agent agent, Caisse caisse, LocalDate date) {
        this.tva = tva;
        this.montantretrait = montantretrait;
        this.montantcaisse = montantcaisse;
        this.agent = agent;
        this.caisse = caisse;
        this.date = date;
    }

    public String getCodetransaction() {
        return codetransaction;
    }

    public void setCodetransaction(String codetransaction) {
        this.codetransaction = codetransaction;
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

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Caisse getCaisse() {
        return caisse;
    }

    public void setCaisse(Caisse caisse) {
        this.caisse = caisse;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
