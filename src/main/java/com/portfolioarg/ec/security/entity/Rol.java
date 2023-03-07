package com.portfolioarg.ec.security.entity;

import com.portfolioarg.ec.security.enums.RolName;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RolName rolName;
    
    //Constructors
    public Rol() {
    }
    public Rol(RolName rolName) {
        this.rolName = rolName;
    }

    //Getters & Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public RolName getRolName() {
        return rolName;
    }
    public void setRolName(RolName rolName) {
        this.rolName = rolName;
    }
}
