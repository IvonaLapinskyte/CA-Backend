package com.ivona.datafood.user;

import com.ivona.datafood.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserMapper {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepo;

    public UserDto toDto(User entity) {

        if (entity == null) {
            return null;
        }

        UserDto dto = new UserDto();

        dto.setUsername(entity.getUsername());
        dto.setPassword(null);
        dto.setEmail(entity.getEmail());

        return dto;
    }
public User fromDto(UserDto dto){

        if (dto == null){
            return null;
        }

        User entity = new User();

        entity.setUsername(dto.getUsername());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setEmail(dto.getEmail());

        if(entity.getRoles() == null) {
            entity.setRoles(new ArrayList<>());
            entity.getRoles().add(roleRepo.findByName("ROLE_USER"));
        }

        return entity;
}
}

