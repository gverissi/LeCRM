package com.example.lecrm.dao;

import com.example.lecrm.entity.Ville;
import com.example.lecrm.repository.VilleRepository;
import org.springframework.stereotype.Repository;

@Repository
public class VilleDaoHibernate implements VilleDaoInterface {

    private final VilleRepository villeRepository;

    public VilleDaoHibernate(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    @Override
    public void save(Ville ville) {
        villeRepository.save(ville);
    }

}
