package com.example.lecrm.dao;

import com.example.lecrm.entity.Ville;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VilleDaoInterface extends CrudRepository<Ville, Integer> {
}
