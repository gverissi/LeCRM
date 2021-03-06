package com.example.lecrm.dao;

import com.example.lecrm.entity.Client;
import com.example.lecrm.repository.ClientRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientDaoHibernate implements ClientDaoInterface {

    private final ClientRepository clientRepository;

    public ClientDaoHibernate(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client findById(Integer id) throws DaoException {
        return clientRepository.findById(id).orElseThrow(() -> new DaoException("There is no client with id = " + id));
    }

    @Override
    public void delete(Client client) {
        clientRepository.delete(client);
    }

    @Override
    public List<Client> findAllByVilleName(String villeName) {
        return clientRepository.findAllByVilleName(villeName);
    }

    @Override
    public List<Client> findAll() {
        return (List<Client>) clientRepository.findAll();
    }

}
