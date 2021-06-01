package com.example.lecrm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClient;
    private String nom;
    private String description;

    @OneToMany(mappedBy="client")
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
