package com.example.lecrm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Passion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPassion;

    private String name;

    @ManyToMany(mappedBy = "passions")
    List<Contact> contacts = new ArrayList<>();

    public Passion(String name) {
        this.name = name;
    }

}
