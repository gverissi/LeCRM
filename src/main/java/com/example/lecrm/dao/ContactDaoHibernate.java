package com.example.lecrm.dao;

import com.example.lecrm.entity.Client;
import com.example.lecrm.entity.Contact;
import com.example.lecrm.repository.ContactRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactDaoHibernate implements ContactDaoInterface {

    private final ContactRepository contactRepository;

    public ContactDaoHibernate(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact findById(Integer id) throws DaoException {
        return contactRepository.findById(id).orElseThrow(() -> new DaoException("There is no contact with id = " + id));
    }

    @Override
    public List<Contact> findAll() {
        return (List<Contact>) contactRepository.findAll();
    }

    @Override
    public void delete(Contact contact) {
        contactRepository.delete(contact);
    }

    @Override
    public List<Contact> findAllByClient(Client client) {
        return contactRepository.findAllByClient(client);
    }

}
