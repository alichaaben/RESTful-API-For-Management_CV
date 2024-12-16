package com.global.hr.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "App_Project")
public class Projets {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomProjet;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String statut;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private AppUser manager;

    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)
    private List<AfficterUserProjet> affectations;

}
