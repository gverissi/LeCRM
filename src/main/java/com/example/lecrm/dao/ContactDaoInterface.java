package com.example.lecrm.dao;

import com.example.lecrm.entity.Client;
import com.example.lecrm.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactDaoInterface extends CrudRepository<Contact, Integer> {

    List<Contact> findAllByClient(Client client);

}
