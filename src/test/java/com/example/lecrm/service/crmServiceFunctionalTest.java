package com.example.lecrm.service;

import com.example.lecrm.dao.DaoException;
import com.example.lecrm.entity.Client;
import com.example.lecrm.entity.Contact;
import com.example.lecrm.entity.Ville;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class crmServiceFunctionalTest {

    @Autowired
    private CrmService crmService;

    @Test
    @Transactional
    void createClient() throws Exception {
        // Given
        Client client = new Client("nom", "description");
        // When
        Client createdClient = crmService.createClient(client);
        // Then
        assertEquals(client.getNom(), createdClient.getNom());
        assertEquals(client.getDescription(), createdClient.getDescription());
    }

    @Test
    @Transactional
    void createContactForClient() throws Exception {
        // Given
        Client client = new Client("nom", "description");
        Contact contact = new Contact("nom", "prenom", LocalDate.now(), "adresse", "email", "tel");
        // When
        crmService.createContactForAClient(client, contact);
        // Then
        assertEquals(1, client.getContacts().size());
        assertEquals(contact, client.getContacts().get(0));
    }

    @Test
    @Transactional
    void findAllContactsByClient() throws Exception {
        // Given
        Client client = new Client("nom", "description");
        Contact contact1 = new Contact("nom1", "prenom", LocalDate.now(), "adresse", "email", "tel");
        Contact contact2 = new Contact("nom2", "prenom", LocalDate.now(), "adresse", "email", "tel");
        crmService.createContactForAClient(client, contact1);
        crmService.createContactForAClient(client, contact2);
        // When
        List<Contact> contacts = crmService.getAllContactsOfAClient(client);
        // Then
        assertEquals(2, contacts.size());
    }

    @Test
    @Transactional
    void findAllClientsByVilleName() throws Exception {
        // Given
        Contact contact1 = new Contact("nom1", "prenom", LocalDate.now(), "adresse", "email", "tel");
        Contact contact2 = new Contact("nom2", "prenom", LocalDate.now(), "adresse", "email", "tel");
        Contact contact3 = new Contact("nom3", "prenom", LocalDate.now(), "adresse", "email", "tel");
        Contact contact4 = new Contact("nom4", "prenom", LocalDate.now(), "adresse", "email", "tel");
        Client client1 = new Client("nom1", "description1");
        Client client2 = new Client("nom2", "description2");
        Client client3 = new Client("nom3", "description3");
        Ville ville1 = new Ville("ville1");
        Ville ville2 = new Ville("ville2");
        crmService.createVilleForAContact(contact1, ville1);
        crmService.createVilleForAContact(contact2, ville1);
        crmService.createVilleForAContact(contact3, ville1);
        crmService.createVilleForAContact(contact4, ville2);
        crmService.createContactForAClient(client1, contact1);
        crmService.createContactForAClient(client2, contact2);
        crmService.createContactForAClient(client2, contact3);
        crmService.createContactForAClient(client3, contact4);
        // When
        List<Client> clients = crmService.findAllClientsWithContactIn("ville1");
        // Then
        System.out.println(clients);
        assertEquals(3, clients.size());
    }

    @Test
    @Transactional
    void updateContact() throws BllException, DaoException {
        // Given
        Client client = new Client("nom", "description");
        Contact contact = new Contact("nom", "prenom", LocalDate.now(), "adresse", "email", "tel");
        crmService.createContactForAClient(client, contact);
        // When
        contact.setNom("new Name");
        crmService.updateContact(contact);
        // Then
        assertEquals("new Name", crmService.getContactById(contact.getIdContact()).getNom());
    }

    @Test
    @Transactional
    void deleteContact() throws BllException {
        // Given
        Client client = new Client("nom", "description");
        Contact contact = new Contact("nom", "prenom", LocalDate.now(), "adresse", "email", "tel");
        crmService.createContactForAClient(client, contact);
        // When
        crmService.deleteContact(contact);
        // Then
        Exception thrown = assertThrows(DaoException.class, () -> crmService.getContactById(contact.getIdContact()));
        assertTrue(thrown.getMessage().contains("There is no contact with id"));
        assertEquals(0, crmService.getAllContactsOfAClient(client).size());
    }

    @Test
    void deleteClient() throws BllException {
        // Given
        Client client = new Client("nom", "description");
        Contact contact = new Contact("nom", "prenom", LocalDate.now(), "adresse", "email", "tel");
        crmService.createContactForAClient(client, contact);
        // When
        crmService.deleteClient(client);
        // Then
        Exception thrownClient = assertThrows(DaoException.class, () -> crmService.getClientById(client.getIdClient()));
        assertTrue(thrownClient.getMessage().contains("There is no client with id"));
        Exception thrownContact = assertThrows(DaoException.class, () -> crmService.getContactById(contact.getIdContact()));
        assertTrue(thrownContact.getMessage().contains("There is no contact with id"));
    }

}