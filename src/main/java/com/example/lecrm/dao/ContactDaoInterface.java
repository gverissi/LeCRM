package com.example.lecrm.dao;

import com.example.lecrm.entity.Client;
import com.example.lecrm.entity.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactDaoInterface {

    Contact save(Contact contact);

    Contact findById(Integer id) throws DaoException;

    List<Contact> findAll();

    void delete(Contact contact);

    List<Contact> findAllByClient(Client client);
}
