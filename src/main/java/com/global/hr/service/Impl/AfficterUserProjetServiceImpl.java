package com.global.hr.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.global.hr.Exceptions.EntityNotFoundException;
import com.global.hr.Exceptions.InvalidEntityException;
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
        return afficterUserProjetRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project assignment not found with ID: " + id));
    }

    @Override
    public List<AfficterUserProjet> findAll() {
        return afficterUserProjetRepo.findAll();
    }

    @Override
    public AfficterUserProjet insert(AfficterUserProjet entity) {
        if (entity.getPoste() == null || entity.getPoste().isEmpty()) {
            throw new InvalidEntityException("Position is required for an assignment.");
        }
        return afficterUserProjetRepo.save(entity);
    }

    @Override
    public AfficterUserProjet update(AfficterUserProjet Entity) {
        AfficterUserProjet currentAffection = afficterUserProjetRepo.findById(Entity.getId())
        .orElseThrow(() -> new IllegalArgumentException("Assignment project not found."));

        currentAffection.setPoste(Entity.getPoste());
        currentAffection.setActivites(Entity.getActivites());
        
        return afficterUserProjetRepo.save(currentAffection);
    }

    @Override
    public void deleteById(Long id) {
        afficterUserProjetRepo.deleteById(id);
    }

}
