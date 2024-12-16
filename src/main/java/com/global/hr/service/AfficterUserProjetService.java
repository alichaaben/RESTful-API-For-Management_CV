package com.global.hr.service;

import java.util.List;

import com.global.hr.entity.AfficterUserProjet;

public interface AfficterUserProjetService {

    AfficterUserProjet findById(Long id);

    List<AfficterUserProjet> findAll();

    AfficterUserProjet insert(AfficterUserProjet Entity);

    AfficterUserProjet update(AfficterUserProjet Entity);

    void deleteById(Long id);

}
