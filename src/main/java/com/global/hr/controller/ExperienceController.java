package com.global.hr.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.dto.ExperienceDto;
import com.global.hr.entity.AppUser;
import com.global.hr.entity.Experience;
import com.global.hr.mapper.ExperienceMapper;
import com.global.hr.repository.AppUserRepo;
import com.global.hr.service.ExperienceService;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/experience")
public class ExperienceController {

    private final ExperienceService experienceService;
    private final ExperienceMapper experienceMapper;
    private final AppUserRepo appUserRepo;

    @GetMapping("/{id}")
    public ResponseEntity<ExperienceDto> findById(@PathVariable Long id) {
        Experience entity = experienceService.findById(id);
        ExperienceDto experienceDto = experienceMapper.map(entity);
        return ResponseEntity.ok(experienceDto);
    }

    @GetMapping()
    public ResponseEntity<List<ExperienceDto>> findAll() {
        List<Experience> entities = experienceService.findAll();
        List<ExperienceDto> experDto = experienceMapper.map(entities);
        return ResponseEntity.ok(experDto);
    }

    @PostMapping()
    public ResponseEntity<ExperienceDto> insert(@RequestBody ExperienceDto dto) {

        AppUser user = appUserRepo.findByUserName(dto.getUserName());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
    
        Experience expe = experienceMapper.unMap(dto);
        expe.setUser(user);
    
        Experience entity = experienceService.insert(expe);
        ExperienceDto rdto = experienceMapper.map(entity);
    
        return ResponseEntity.ok(rdto);
    }
    

    @PutMapping()
    public ResponseEntity<ExperienceDto> update(@RequestBody ExperienceDto dto) {

        Experience currentExperience = experienceService.findById(dto.getId());
        experienceMapper.updateEntityFromDto(currentExperience, dto);
        Experience updatedExperience = experienceService.update(currentExperience);
        ExperienceDto responseDto = experienceMapper.map(updatedExperience);

        return ResponseEntity.ok(responseDto);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        experienceService.deleteById(id);
    }

}
