package com.global.hr.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.global.hr.entity.Projets;
import com.global.hr.repository.ProjetsRepo;
import com.global.hr.service.ProjetsService;
import com.global.hr.Exceptions.EntityNotFoundException;
import com.global.hr.Exceptions.InvalidEntityException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjetsServiceImpl implements ProjetsService{

    private final ProjetsRepo projetsRepo;
    
    @Override
    public Projets findById(Long id) {
        return projetsRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + id));
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
    public Projets insert(Projets entity) {
        if (entity.getNomProjet() == null || entity.getNomProjet().isEmpty()) {
            throw new InvalidEntityException("Project name is required.");
        }
        return projetsRepo.save(entity);
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
