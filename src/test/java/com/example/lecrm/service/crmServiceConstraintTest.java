package com.example.lecrm.service;

import com.example.lecrm.entity.Client;
import com.example.lecrm.entity.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class crmServiceConstraintTest {

    @Autowired
    private CrmService crmService;

    @Test
    @Transactional
    void createClient_Exception() {
        // Given
        Client client = new Client("Duponds", "description");
        // When
        Exception thrown = assertThrows(BllException.class, () -> crmService.createClient(client));
        // Then
        assertTrue(thrown.getMessage().contains("Duponds"));
    }

    @Test
    @Transactional
    void createContactForClient_Exception() throws Exception {
        // Given
        Client client = new Client("nom", "description");
        Contact contact1 = new Contact("nom1", "prenom", LocalDate.now(), "adresse", "email", "tel");
        Contact contact2 = new Contact("nom2", "prenom", LocalDate.now(), "adresse", "email", "tel");
        Contact contact3 = new Contact("nom3", "prenom", LocalDate.now(), "adresse", "email", "tel");
        Contact contact4 = new Contact("nom4", "prenom", LocalDate.now(), "adresse", "email", "tel");
        // When
        crmService.createContactForAClient(client, contact1);
        crmService.createContactForAClient(client, contact2);
        crmService.createContactForAClient(client, contact3);
        Exception thrown = assertThrows(BllException.class, () -> crmService.createContactForAClient(client, contact4));
        // Then
        assertTrue(thrown.getMessage().contains("3 contacts"));
    }

}