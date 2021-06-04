package com.example.lecrm.api;

import com.example.lecrm.entity.Client;
import com.example.lecrm.entity.Contact;
import com.example.lecrm.entity.Ville;
import com.example.lecrm.service.BllException;
import com.example.lecrm.service.CrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientWS {

    private final CrmService crmService;

    @Autowired
    public ClientWS(CrmService crmService) throws BllException {
        this.crmService = crmService;
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
    }


    @GetMapping("/clients/{villeName}")
    public List<Client> getAllClientsWithContactIn(@PathVariable String villeName) {
        return crmService.findAllClientsWithContactIn(villeName);
    }

}
