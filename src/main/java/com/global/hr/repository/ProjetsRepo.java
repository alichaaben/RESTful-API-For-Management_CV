package com.global.hr.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.hr.entity.Projets;

@Repository
public interface ProjetsRepo extends JpaRepository <Projets,Long> {

    Projets findByNomProjet(String nomProjet);

}
