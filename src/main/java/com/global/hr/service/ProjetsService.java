package com.global.hr.service;

import java.util.List;

import com.global.hr.entity.Projets;

public interface ProjetsService {

    Projets findById(Long id);

    List<Projets> findAll();

    Projets findByNomProjet(String nomProjet);

    Projets insert(Projets Entity);

    Projets update(Projets Entity);
    
    void deleteById(Long id);

}
