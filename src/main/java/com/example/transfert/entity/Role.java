package com.example.transfert.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rolename;
    private Boolean valide;

    public Role(Long id, String rolename) {
        this.id = id;
        this.rolename = rolename;
        this.valide = true;
    }

    public Role(String rolename) {
        this.rolename = rolename;
    }

    public Role(String rolename, Boolean valide) {
        this.rolename = rolename;
        this.valide = valide;
    }

    public Role() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Boolean getValide() {
        return valide;
    }

    public void setValide(Boolean valide) {
        this.valide = valide;
    }
}
