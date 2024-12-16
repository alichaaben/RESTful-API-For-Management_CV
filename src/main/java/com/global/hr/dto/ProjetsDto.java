package com.global.hr.dto;

import java.time.LocalDate;


import lombok.Data;

@Data
public class ProjetsDto {

    private Long id;
    private String nomProjet;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String statut;
    private String userName;

}
