package com.global.hr.mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.global.hr.dto.ProjetsDto;
import com.global.hr.entity.Projets;



@Mapper(componentModel = "spring")
public interface ProjetsMapper {

    
    @Mapping(source = "nomProjet", target = "nomProjet")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "dateDebut", target = "dateDebut")
    @Mapping(source = "dateFin", target = "dateFin")
    @Mapping(source = "statut", target = "statut")
    @Mapping(source = "manager.userName" , target = "userName")
    ProjetsDto map(Projets entity);
    
    List<ProjetsDto> map(List<Projets> entities);

    
    @Mapping(source = "nomProjet", target = "nomProjet")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "dateDebut", target = "dateDebut")
    @Mapping(source = "dateFin", target = "dateFin")
    @Mapping(source = "statut", target = "statut")
    @Mapping(target = "affectations", ignore = true)
    @Mapping(source = "userName", target = "manager", ignore = true)
    Projets unMap(ProjetsDto dto);

    
    @Mapping(source = "nomProjet", target = "nomProjet")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "dateDebut", target = "dateDebut")
    @Mapping(source = "dateFin", target = "dateFin")
    @Mapping(source = "statut", target = "statut")
    @Mapping(target = "affectations", ignore = true)
    @Mapping(source = "userName", target = "manager", ignore = true)
    void updateEntityFromDto(@MappingTarget Projets entity, ProjetsDto dto);


}
