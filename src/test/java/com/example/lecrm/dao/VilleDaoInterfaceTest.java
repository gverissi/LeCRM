package com.example.lecrm.dao;

import com.example.lecrm.entity.Contact;
import com.example.lecrm.entity.Ville;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class VilleDaoInterfaceTest {

    @Autowired
    ContactDaoInterface contactDao;

    @Autowired
    VilleDaoInterface villeDao;

    @Test
    void create() throws DaoException {
        // Given
        Contact contact1 = new Contact("nom1", "prenom1", LocalDate.now(), "adresse1", "email1", "tel1");
        Contact contact2 = new Contact("nom2", "prenom2", LocalDate.now(), "adresse2", "email2", "tel2");
        Ville ville = new Ville("ma Ville");
        contact1.setVille(ville);
        contact2.setVille(ville);
        // When
        villeDao.save(ville);
        Contact savedContact1 = contactDao.save(contact1);
        Contact savedContact2 = contactDao.save(contact2);
        // Then
        assertNotNull(savedContact1.getIdContact());
        assertNotNull(savedContact2.getIdContact());
        assertEquals("ma Ville", savedContact1.getVille().getNom());
        assertEquals("ma Ville", savedContact2.getVille().getNom());
        Contact savedContact22 = contactDao.findById(savedContact2.getIdContact());
        assertEquals("ma Ville", savedContact22.getVille().getNom());
    }

}