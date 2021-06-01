package com.example.lecrm.service;

import com.example.lecrm.dao.ClientDaoInterface;
import com.example.lecrm.dao.ContactDaoInterface;
import com.example.lecrm.dao.VilleDaoInterface;
import com.example.lecrm.entity.Client;
import com.example.lecrm.entity.Contact;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class crmServiceTestMock {

    private final ContactDaoInterface contactDaoMock = mock(ContactDaoInterface.class);
    private final ClientDaoInterface clientDaoMock = mock(ClientDaoInterface.class);
    private final VilleDaoInterface villeDaoMock = mock(VilleDaoInterface.class);

    private final CrmService crmService = new CrmService(contactDaoMock, clientDaoMock, villeDaoMock);

    @BeforeAll
    void init() {
        when(clientDaoMock.save(any(Client.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
    }

    @Test
    void createClient() throws Exception {
        // Given
        Client client = new Client("nom", "description");
        // When
        Client createdClient = crmService.createClient(client);
        // Then
        verify(clientDaoMock).save(client);
        assertEquals(client.getNom(), createdClient.getNom());
        assertEquals(client.getDescription(), createdClient.getDescription());
    }

    @Test
    void createClient_Exception() {
        // Given
        Client client = new Client("Duponds", "description");
        // When
        Exception thrown = assertThrows(Exception.class, () -> crmService.createClient(client));
        // Then
        assertTrue(thrown.getMessage().contains("Duponds"));
    }

    @Test
    void createContactForClient() throws Exception {
        // Given
        Client client = new Client("nom", "description");
        Contact contact = new Contact("nom", "prenom", LocalDate.now(), "adresse", "email", "tel");
        // When
        crmService.createContactForClient(client, contact);
        // Then
        verify(clientDaoMock).save(client);
        verify(contactDaoMock).save(contact);
    }

    @Test
    void createContactForClient_Exception() throws Exception {
        // Given
        Client client = new Client("nom", "description");
        Contact contact1 = new Contact("nom1", "prenom", LocalDate.now(), "adresse", "email", "tel");
        Contact contact2 = new Contact("nom2", "prenom", LocalDate.now(), "adresse", "email", "tel");
        Contact contact3 = new Contact("nom3", "prenom", LocalDate.now(), "adresse", "email", "tel");
        Contact contact4 = new Contact("nom4", "prenom", LocalDate.now(), "adresse", "email", "tel");
        // When
        crmService.createContactForClient(client, contact1);
        crmService.createContactForClient(client, contact2);
        crmService.createContactForClient(client, contact3);
        Exception thrown = assertThrows(Exception.class, () -> crmService.createContactForClient(client, contact4));
        // Then
        assertTrue(thrown.getMessage().contains("3 contacts"));
    }

}