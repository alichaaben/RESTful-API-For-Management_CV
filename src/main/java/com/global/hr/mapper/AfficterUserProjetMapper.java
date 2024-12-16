package com.global.hr.mapper;

import java.util.List;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.global.hr.dto.AfficterUserProjetDto;
import com.global.hr.entity.AfficterUserProjet;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AfficterUserProjetMapper {


    
    @Mapping(source = "poste", target = "poste")
    @Mapping(source = "activites", target = "activites")
    @Mapping(source = "user.userName", target = "employe")
    @Mapping(source = "projet.nomProjet", target = "projet")
    AfficterUserProjetDto map(AfficterUserProjet entity);


    
    List<AfficterUserProjetDto> map(List<AfficterUserProjet> entities);

    
    @Mapping(source = "poste", target = "poste")
    @Mapping(source = "activites", target = "activites")
    @Mapping(source = "employe", target = "user", ignore = true)
    @Mapping(source = "projet", target = "projet", ignore = true)
    AfficterUserProjet unMap(AfficterUserProjetDto dto);

    
    @Mapping(source = "poste", target = "poste")
    @Mapping(source = "activites", target = "activites")
    @Mapping(source = "employe", target = "user", ignore = true)
    @Mapping(source = "projet", target = "projet", ignore = true)
    void updateEntityFromDto(@MappingTarget AfficterUserProjet entity, AfficterUserProjetDto dto);



}
