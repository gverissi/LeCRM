package com.example.lecrm.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idClient")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClient;
    private String nom;
    private String description;

//    @JsonManagedReference
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy="client")
    private List<Contact> contacts = new ArrayList<>();

    public Client(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        contact.setClient(this);
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
