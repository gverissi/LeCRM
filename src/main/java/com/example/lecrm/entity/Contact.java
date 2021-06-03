package com.example.lecrm.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idContact")
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

//    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name="ville_id")
    private Ville ville;

    @ManyToMany
    @JoinTable(
            name = "contact_passion",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "passion_id"))
    List<Passion> passions = new ArrayList<>();

    public Contact(String nom, String prenom, LocalDate dtCreate, String adresse, String email, String tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.dtCreate = dtCreate;
        this.adresse = adresse;
        this.email = email;
        this.tel = tel;
    }

    public void addPassion(Passion passion) {
        passions.add(passion);
        passion.contacts.add(this);
    }

}
