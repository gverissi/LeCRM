package com.example.lecrm.dao;

import com.example.lecrm.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDaoInterface extends CrudRepository<Contact, Integer> {
}
