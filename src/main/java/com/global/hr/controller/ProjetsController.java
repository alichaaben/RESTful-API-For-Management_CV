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

import com.global.hr.dto.ProjetsDto;
import com.global.hr.entity.AppUser;
import com.global.hr.entity.Projets;
import com.global.hr.mapper.ProjetsMapper;
import com.global.hr.repository.AppUserRepo;
import com.global.hr.service.ProjetsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/projets")
public class ProjetsController {
    
    private final ProjetsService projetsService;
    private final ProjetsMapper projetsMapper;
    private final AppUserRepo appUserRepo;

    @GetMapping("/{id}")
    public ResponseEntity<ProjetsDto> findById(@PathVariable Long id) {
        Projets entity = projetsService.findById(id);
        ProjetsDto projetsDto = projetsMapper.map(entity);
        return ResponseEntity.ok(projetsDto);
    }

    @GetMapping()
    public ResponseEntity<List<ProjetsDto>> findAll() {
        List<Projets> entities = projetsService.findAll();
        List<ProjetsDto> projetsDto = projetsMapper.map(entities);
        return ResponseEntity.ok(projetsDto);
    }


    @PostMapping()
    public ResponseEntity<ProjetsDto> insert(@RequestBody ProjetsDto dto) {
        AppUser manager = appUserRepo.findByUserName(dto.getUserName());
        if (manager == null) {
            throw new RuntimeException("User not found");
        }

        Projets projet = projetsMapper.unMap(dto);
        projet.setManager(manager);
    
        Projets entity = projetsService.insert(projet);
        ProjetsDto rdto = projetsMapper.map(entity);
    
        return ResponseEntity.ok(rdto);
    }

    @PutMapping()
    public ResponseEntity<ProjetsDto> update(@RequestBody ProjetsDto dto) {

        Projets currentProjets = projetsService.findById(dto.getId());
        projetsMapper.updateEntityFromDto(currentProjets, dto);
        Projets updatedProjets = projetsService.update(currentProjets);
        ProjetsDto responseDto = projetsMapper.map(updatedProjets);

        return ResponseEntity.ok(responseDto);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        projetsService.deleteById(id);
    }

}
