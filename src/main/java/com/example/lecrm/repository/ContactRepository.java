package com.example.lecrm.repository;

import com.example.lecrm.entity.Client;
import com.example.lecrm.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {

    List<Contact> findAllByClient(Client client);

}
