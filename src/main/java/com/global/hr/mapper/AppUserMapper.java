package com.global.hr.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.global.hr.dto.AppUserDto;
import com.global.hr.entity.AppUser;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

     // Mapping user -> UserDto
    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telephone", target = "telephone")
    @Mapping(source = "motDePasse", target = "motDePasse")
    @Mapping(source = "role.roleName", target = "roleName")
    AppUserDto map(AppUser entity);


    // Mapping List<User> -> List<AppUserDto>
    List<AppUserDto> map(List<AppUser> entities);

    // Mapping AppUserDto -> User
    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telephone", target = "telephone")
    @Mapping(source = "motDePasse", target = "motDePasse")
    @Mapping(source = "roleName", target = "role", ignore = true)
    @Mapping(target = "projets", ignore = true)
    AppUser unMap(AppUserDto dto);

    // Mapping AppUserDto -> User pour mise à jour
    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telephone", target = "telephone")
    @Mapping(source = "motDePasse", target = "motDePasse")
    @Mapping(source = "roleName", target = "role", ignore = true)
    @Mapping(target = "projets", ignore = true)
    void updateEntityFromDto(@MappingTarget AppUser entity, AppUserDto dto);


}