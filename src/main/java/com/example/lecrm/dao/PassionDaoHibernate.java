package com.example.lecrm.dao;

import com.example.lecrm.entity.Passion;
import com.example.lecrm.repository.PassionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PassionDaoHibernate implements PassionDaoInterface {

    private final PassionRepository passionRepository;

    @Autowired
    public PassionDaoHibernate(PassionRepository passionRepository) {
        this.passionRepository = passionRepository;
    }

    @Override
    public Passion save(Passion passion) {
        return passionRepository.save(passion);
    }

}
