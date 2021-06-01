package com.example.lecrm.dao;

import com.example.lecrm.entity.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContactDaoInterfaceTest {

    @Autowired
    ContactDaoInterface contactDao;

    @Test
    void create() {
        // Given
        Contact contact = new Contact("nom", "prenom", LocalDate.now(), "adresse", "email", "tel");
        // When
        contactDao.save(contact);
        // Then
        assertNotNull(contact.getIdContact());
    }

    @Test
    void read() {
        // Given
        Contact contact = new Contact("nom", "prenom", LocalDate.now(), "adresse", "email", "tel");
        contactDao.save(contact);
        // When
        Contact savedContact = contactDao.findById(contact.getIdContact()).orElse(null);
        // Then
        assertNotNull(savedContact);
        assertEquals(contact, savedContact);
    }

    @Test
    void update() {
        // Given
        Contact contact = new Contact("nom", "prenom", LocalDate.now(), "adresse", "email", "tel");
        contactDao.save(contact);
        Integer idContact = contact.getIdContact();
        int nbContacts = ((Collection<?>) contactDao.findAll()).size();
        // When
        String newName = "myName";
        contact.setNom(newName);
        contactDao.save(contact);
        Contact updatedContact = contactDao.findById(idContact).orElse(null);
        int nbContactsAfterUpdate = ((Collection<?>) contactDao.findAll()).size();
        // Then
        assertNotNull(updatedContact);
        assertEquals(contact, updatedContact);
        assertEquals(newName, updatedContact.getNom());
        assertEquals(nbContacts, nbContactsAfterUpdate);
    }

    @Test
    void delete() {
        // Given
        Contact contact1 = new Contact("nom1", "prenom1", LocalDate.now(), "adresse1", "email1", "tel1");
        contactDao.save(contact1);
        Contact contact2 = new Contact("nom2", "prenom2", LocalDate.now(), "adresse2", "email2", "tel2");
        contactDao.save(contact2);
        int nbContacts = ((Collection<?>) contactDao.findAll()).size();
        // When
        contactDao.delete(contact2);
        // Then
        assertEquals(nbContacts - 1, ((Collection<?>) contactDao.findAll()).size());
    }

}