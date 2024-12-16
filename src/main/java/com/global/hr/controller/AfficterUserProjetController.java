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

import com.global.hr.dto.AfficterUserProjetDto;
import com.global.hr.entity.AfficterUserProjet;
import com.global.hr.entity.AppUser;
import com.global.hr.entity.Projets;
import com.global.hr.mapper.AfficterUserProjetMapper;
import com.global.hr.repository.AppUserRepo;
import com.global.hr.repository.ProjetsRepo;
import com.global.hr.service.AfficterUserProjetService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/affictation")
public class AfficterUserProjetController {

    private final AfficterUserProjetService afficterUserProjetService;
    private final AfficterUserProjetMapper afficterUserProjetMapper;
    private final AppUserRepo appUserRepo;
    private final ProjetsRepo projetsRepo;

    @GetMapping("/{id}")
    public ResponseEntity<AfficterUserProjetDto> findById(@PathVariable Long id) {
        AfficterUserProjet entity = afficterUserProjetService.findById(id);
        AfficterUserProjetDto rDto = afficterUserProjetMapper.map(entity);
        return ResponseEntity.ok(rDto);
    }

    @GetMapping()
    public ResponseEntity<List<AfficterUserProjetDto>> findAll() {
        List<AfficterUserProjet> entities = afficterUserProjetService.findAll();
        List<AfficterUserProjetDto> rDto = afficterUserProjetMapper.map(entities);
        return ResponseEntity.ok(rDto);
    }

    @PostMapping()
    public ResponseEntity<AfficterUserProjetDto> insert(@RequestBody AfficterUserProjetDto dto) {

        AppUser user = appUserRepo.findByUserName(dto.getEmploye());
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Projets Projet = projetsRepo.findByNomProjet(dto.getProjet());
        if (Projet == null) {
            throw new RuntimeException("Projet not found");
        }

        AfficterUserProjet AffProj = afficterUserProjetMapper.unMap(dto);
        AffProj.setUser(user);
        AffProj.setProjet(Projet);
        
        AfficterUserProjet entity = afficterUserProjetService.insert(AffProj);
        AfficterUserProjetDto rdto = afficterUserProjetMapper.map(entity);


        return ResponseEntity.ok(rdto);
    }

    @PutMapping()
    public ResponseEntity<AfficterUserProjetDto> update(@RequestBody AfficterUserProjetDto dto) {

        AfficterUserProjet currentAfficterUserProjet = afficterUserProjetService.findById(dto.getId());
        afficterUserProjetMapper.updateEntityFromDto(currentAfficterUserProjet, dto);
        AfficterUserProjet updatedAfficterUserProjet = afficterUserProjetService.update(currentAfficterUserProjet);
        AfficterUserProjetDto responseDto = afficterUserProjetMapper.map(updatedAfficterUserProjet);

        return ResponseEntity.ok(responseDto);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        afficterUserProjetService.deleteById(id);
    }

}