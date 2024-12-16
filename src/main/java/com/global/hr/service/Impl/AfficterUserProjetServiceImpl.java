package com.global.hr.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.global.hr.entity.AfficterUserProjet;
import com.global.hr.repository.AfficterUserProjetRepo;
import com.global.hr.service.AfficterUserProjetService;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AfficterUserProjetServiceImpl implements AfficterUserProjetService {

    private final AfficterUserProjetRepo afficterUserProjetRepo;
    
    @Override
    public AfficterUserProjet findById(Long id) {
        return afficterUserProjetRepo.findById(id).orElse(null);
    }

    @Override
    public List<AfficterUserProjet> findAll() {
        return afficterUserProjetRepo.findAll();
    }

    @Override
    public AfficterUserProjet insert(AfficterUserProjet Entity) {
        return afficterUserProjetRepo.save(Entity);
    }

    @Override
    public AfficterUserProjet update(AfficterUserProjet Entity) {
        AfficterUserProjet currentAffection = afficterUserProjetRepo.findById(Entity.getId())
        .orElseThrow(() -> new IllegalArgumentException("Affiction Project not found"));

        currentAffection.setPoste(Entity.getPoste());
        currentAffection.setActivites(Entity.getActivites());
        
        return afficterUserProjetRepo.save(currentAffection);
    }

    @Override
    public void deleteById(Long id) {
        afficterUserProjetRepo.deleteById(id);
    }

}
