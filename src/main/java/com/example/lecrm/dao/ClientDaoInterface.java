package com.example.lecrm.dao;

import com.example.lecrm.entity.Client;

import java.util.List;

public interface ClientDaoInterface {

    Client save(Client client);

    Client findById(Integer id) throws DaoException;

    List<Client> findAllByVilleName(String villeName);

}
