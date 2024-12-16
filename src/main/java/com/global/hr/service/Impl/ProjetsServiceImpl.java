package com.global.hr.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.global.hr.entity.Projets;
import com.global.hr.repository.ProjetsRepo;
import com.global.hr.service.ProjetsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjetsServiceImpl implements ProjetsService{

    private final ProjetsRepo projetsRepo;
    
    @Override
    public Projets findById(Long id) {
        return projetsRepo.findById(id).orElse(null);
    }

    @Override
    public List<Projets> findAll() {
        return projetsRepo.findAll();
    }

    @Override
    public Projets findByNomProjet(String nomProjet) {
        return projetsRepo.findByNomProjet(nomProjet);
    }

    @Override
    public Projets insert(Projets Entity) {
        return projetsRepo.save(Entity);
    }

    @Override
    public Projets update(Projets Entity) {
        Projets currentProjet = projetsRepo.findById(Entity.getId())
        .orElseThrow(() -> new IllegalArgumentException("Projets not found"));

        currentProjet.setNomProjet(Entity.getNomProjet());
        currentProjet.setDescription(Entity.getDescription());
        currentProjet.setDateDebut(Entity.getDateDebut());
        currentProjet.setDateFin(Entity.getDateFin());
        currentProjet.setStatut(Entity.getStatut());
        
        return projetsRepo.save(currentProjet);
    }

    @Override
    public void deleteById(Long id) {
        projetsRepo.deleteById(id);

    }


}
