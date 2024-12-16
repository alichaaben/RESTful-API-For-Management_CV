package com.global.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.hr.entity.AfficterUserProjet;




@Repository
public interface AfficterUserProjetRepo extends JpaRepository <AfficterUserProjet,Long>{

}
