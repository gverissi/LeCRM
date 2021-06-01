package com.example.lecrm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idContact;
    private String nom;
    private String prenom;
    private LocalDate dtCreate;
    private String adresse;
    private String email;
    private String tel;

    public Contact(String nom, String prenom, LocalDate dtCreate, String adresse, String email, String tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.dtCreate = dtCreate;
        this.adresse = adresse;
        this.email = email;
        this.tel = tel;
    }

}
