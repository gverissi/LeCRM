package com.example.lecrm.dao;

import com.example.lecrm.entity.Client;
import com.example.lecrm.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDaoInterface extends CrudRepository<Client, Integer> {
}
