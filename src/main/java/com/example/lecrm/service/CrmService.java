package com.example.lecrm.service;

import com.example.lecrm.dao.*;
import com.example.lecrm.entity.Client;
import com.example.lecrm.entity.Contact;
import com.example.lecrm.entity.Passion;
import com.example.lecrm.entity.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CrmService {

    private final ContactDaoInterface contactDao;
    private final ClientDaoInterface clientDao;
    private final VilleDaoInterface villeDao;
    private final PassionDaoInterface passionDao;

    @Autowired
    public CrmService(ContactDaoInterface contactDao, ClientDaoInterface clientDao, VilleDaoInterface villeDao, PassionDaoInterface passionDao) {
        this.contactDao = contactDao;
        this.clientDao = clientDao;
        this.villeDao = villeDao;
        this.passionDao = passionDao;
    }

    /**
     * Creates a Client entity e.g. persist in database. If the input parameter client has a valid id (exist in database),
     * then updates the existing Client entity.
     * If the input client parameter doesn't fits the constraints then, throw a BllException.
     * @param client the entity to be persisted
     * @return the persisted entity with the autogenerated id
     * @throws BllException if client name is Duponds
     */
    @Transactional
    public Client createClient(Client client) throws BllException {
        if (client.getNom().equals("Duponds")) throw new BllException("Pas de Duponds chez nous!");
        return clientDao.save(client);
    }

    /**
     * Creates a Contact entity (e.g. persist) and associates it with a Client entity (Many-to-One: many contacts has one client).
     * If the input parameter contact has a valid id, then updates it. If the input parameter client doesn't exist,
     * then creates it otherwise, updates it.
     * A client entity can have a maximum of 3 contacts. Trying the add more then 3 contacts to a client will throw a BllException.
     * @param client parent of the contact
     * @param contact entity that will be associated to a client and persisted
     * @throws BllException if the client entity has already 3 contacts
     */
    @Transactional
    public void createContactForAClient(Client client, Contact contact) throws BllException {
        if (client.getContacts().size() == 3) throw new BllException("Pas plus de 3 contacts!");
        client.addContact(contact);
        createClient(client);
        contactDao.save(contact);
    }

    /**
     * Creates a Ville entity (e.g. persist) and associates it with a Contact entity (One-to-Many: ville has many contacts).
     * if the input parameter ville has a valid id, then updates it.
     * If the input parameter Contact doesn't exist, then creates it otherwise, updates it.
     * @param contact parent of the ville
     * @param ville entity that will be associated to a contact and persisted
     */
    @Transactional
    public void createVilleForAContact(Contact contact, Ville ville) {
        contact.setVille(ville);
        villeDao.save(ville);
        contactDao.save(contact);
    }

    public List<Contact> getAllContactsOfAClient(Client client) {
        return contactDao.findAllByClient(client);
    }

    public List<Client> findAllClientsWithContactIn(String villeName) {
        return clientDao.findAllByVilleName(villeName);
    }

    public void updateContact(Contact contact) {
        contactDao.save(contact);
    }

    public void deleteContact(Contact contact) {
        contactDao.delete(contact);
    }

    public void deleteClient(Client client) {
        clientDao.delete(client);
    }

    public Contact getContactById(Integer idContact) throws DaoException {
        return contactDao.findById(idContact);
    }

    public Client getClientById(Integer idClient) throws DaoException {
        return clientDao.findById(idClient);
    }

    @Transactional
    public Passion createPassion(Passion passion) {
        return passionDao.save(passion);
    }

    @Transactional
    public void addPassionForAContact(Contact contact, Passion passion) throws BllException {
        if (contact.getIdContact() == null) throw new BllException("Contact must exist in database to adding a passion!");
        contact.addPassion(passion);
        passionDao.save(passion);
        contactDao.save(contact);
    }

    public List<Client> findAllClients() {
        return clientDao.findAll();
    }

    public void updateClient(Client client) throws BllException {
        createClient(client);
    }

    public void deleteClientById(Integer id) throws DaoException {
        Client client = getClientById(id);
        clientDao.delete(client);
    }
}
