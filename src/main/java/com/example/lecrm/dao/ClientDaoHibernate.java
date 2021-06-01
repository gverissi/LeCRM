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
        return clientRepository.findById(id).orElseThrow(() -> new DaoException("There is no Client with id = " + id));
    }

    @Override
    public List<Client> findAllByVilleName(String villeName) {
        return clientRepository.findAllByVilleName(villeName);
    }

}
