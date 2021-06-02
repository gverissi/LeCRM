package com.example.lecrm.repository;

import com.example.lecrm.entity.Passion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassionRepository extends CrudRepository<Passion, Integer> {
}
