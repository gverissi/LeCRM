package com.example.lecrm.dao;

import com.example.lecrm.entity.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDaoInterface extends CrudRepository<Client, Integer> {

    @Query("select c.client from Contact c where c.ville.nom=:villeName")
    List<Client> findAllByVilleName(@Param("villeName") String villeName);

}
