package com.example.lecrm.dao;

import com.example.lecrm.entity.Client;
import com.example.lecrm.entity.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ClientDaoInterfaceTest {

    @Autowired
    ContactDaoInterface contactDao;

    @Autowired
    ClientDaoInterface clientDao;

    @Test
    @Transactional
    void create() {
        // Given
        Contact contact1 = new Contact("nom1", "prenom1", LocalDate.now(), "adresse1", "email1", "tel1");
        Contact contact2 = new Contact("nom2", "prenom2", LocalDate.now(), "adresse2", "email2", "tel2");
        Client client = new Client("nom", "description");
        client.addContact(contact1);
        client.addContact(contact2);
        // When
        clientDao.save(client);
        contactDao.save(contact1);
        contactDao.save(contact2);
        Client savedClient = clientDao.findById(client.getIdClient()).orElse(null);
        // Then
        assertNotNull(savedClient);
        assertNotNull(client.getIdClient());
        assertNotNull(contact1.getIdContact());
        assertNotNull(contact2.getIdContact());
        assertEquals(2, savedClient.getContacts().size());
        assertEquals("nom1", savedClient.getContacts().get(0).getNom());
    }

}