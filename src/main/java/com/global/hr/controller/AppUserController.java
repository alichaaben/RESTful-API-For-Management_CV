package com.global.hr.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.global.hr.dto.AppUserDto;
import com.global.hr.entity.AppUser;
import com.global.hr.entity.Roles;
import com.global.hr.mapper.AppUserMapper;
import com.global.hr.service.AppUserService;
import com.global.hr.repository.RolesRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class AppUserController {

    private final AppUserService appUserService;
    private final AppUserMapper appUserMapper;
    private final RolesRepo rolesRepo;

    @GetMapping("/{id}")
    public ResponseEntity<AppUserDto> findById(@PathVariable Long id) {
        AppUser entity = appUserService.findById(id);
        AppUserDto userDto = appUserMapper.map(entity);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping()
    public ResponseEntity<List<AppUserDto>> findAll() {
        List<AppUser> entities = appUserService.findAll();
        List<AppUserDto> userDtos = appUserMapper.map(entities);
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/filtre")
    public ResponseEntity<AppUserDto> filtre(@RequestParam String userName) {
        AppUser entity = appUserService.findByUserName(userName);
        AppUserDto userDto = appUserMapper.map(entity);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping()
    public ResponseEntity<AppUserDto> insert(@RequestBody AppUserDto dto) {
        
        Roles role = rolesRepo.findByRoleName(dto.getRoleName())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        AppUser user = appUserMapper.unMap(dto);
        user.setRole(role);

        AppUser entity = appUserService.insert(user);
        AppUserDto responseDto = appUserMapper.map(entity);

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping()
    public ResponseEntity<AppUserDto> update(@RequestBody AppUserDto dto) {
        AppUser currentUser = appUserService.findById(dto.getId());

        appUserMapper.updateEntityFromDto(currentUser, dto);

        Roles role = rolesRepo.findByRoleName(dto.getRoleName())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        currentUser.setRole(role);

        AppUser updatedUser = appUserService.update(currentUser);
        AppUserDto responseDto = appUserMapper.map(updatedUser);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        appUserService.deleteById(id);
    }
}
