package com.example.lecrm.service;

import com.example.lecrm.dao.ClientDaoInterface;
import com.example.lecrm.dao.ContactDaoInterface;
import com.example.lecrm.dao.VilleDaoInterface;
import com.example.lecrm.entity.Client;
import com.example.lecrm.entity.Contact;
import com.example.lecrm.entity.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmService {

    private final ContactDaoInterface contactDao;
    private final ClientDaoInterface clientDao;
    private final VilleDaoInterface villeDao;

    @Autowired
    public CrmService(ContactDaoInterface contactDao, ClientDaoInterface clientDao, VilleDaoInterface villeDao) {
        this.contactDao = contactDao;
        this.clientDao = clientDao;
        this.villeDao = villeDao;
    }

    public Client createClient(Client client) throws Exception {
        if (client.getNom().equals("Duponds")) throw new Exception("Pas de Duponds chez nous!");
        return clientDao.save(client);
    }

    public void createContactForClient(Client client, Contact contact) throws Exception {
        if (client.getContacts().size() == 3) throw new Exception("Pas plus de 3 contacts!");
        client.addContact(contact);
        createClient(client);
        contactDao.save(contact);
    }

    public void createVilleForContact(Contact contact, Ville ville) {
        contact.setVille(ville);
        villeDao.save(ville);
        contactDao.save(contact);
    }

    public List<Contact> findAllContactsByClient(Client client) {
        return contactDao.findAllByClient(client);
    }

    public List<Client> findAllClientsWithContactIn(String villeName) {
        return clientDao.findAllByVilleName(villeName);
    }

    public void updateContact() {

    }

    public void deleteContact() {

    }

    public void deleteClient() {

    }

}
