package com.global.hr.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.global.hr.Exceptions.EntityNotFoundException;
import com.global.hr.Exceptions.InvalidEntityException;

import com.global.hr.entity.Experience;
import com.global.hr.repository.ExperienceRepo;
import com.global.hr.service.ExperienceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepo experienceRepo;
    
    @Override
    public Experience findById(Long id) {
        return experienceRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Experience not found with ID: " + id));
    }

    @Override
    public List<Experience> findAll() {
        return experienceRepo.findAll();
    }

    @Override
    public Experience insert(Experience entity) {
        if (entity.getTitreExperience() == null || entity.getTitreExperience().isEmpty()) {
            throw new InvalidEntityException("Experience title is required.");
        }
        return experienceRepo.save(entity);
    }
    
    @Override
    public Experience update(Experience Entity) {
        Experience currentExperience = experienceRepo.findById(Entity.getId())
        .orElseThrow(() -> new IllegalArgumentException("User not found"));

        currentExperience.setTitreExperience(Entity.getTitreExperience());
        currentExperience.setPoste(Entity.getPoste());
        currentExperience.setActivites(Entity.getActivites());
        
        return experienceRepo.save(currentExperience);
    }

    @Override
    public void deleteById(Long id) {
        experienceRepo.deleteById(id);
    }

}
