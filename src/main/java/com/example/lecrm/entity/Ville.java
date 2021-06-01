package com.example.lecrm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Ville {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVille;
    private String nom;

    public Ville(String nom) {
        this.nom = nom;
    }

}
