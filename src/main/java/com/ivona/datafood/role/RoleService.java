package com.ivona.datafood.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private RoleMapper roleMapper;


    public RoleDto addRole(RoleDto role) {

        if (role == null) {
            return null;
        }
        roleRepo.save(roleMapper.fromDto(role));
        return role;
    }

    public RoleDto getRole(String rolename) {
        if (rolename == null) {
            return null;
        }
        Role role = roleRepo.findByName(rolename);
        if (role == null) {
            return null;
        }


        return roleMapper.toDto(role);

    }
    public String updateRole(Integer id, RoleDto role){
        if (id == null || role == null) {
            return null;
        }

        Role findRole = roleRepo.findById(id).orElse(null);

        if (findRole == null){
            return "Role with id "+id+" was not found!";
        }
        Role transfer = roleMapper.fromDto(role);
        transfer.setId(id);
        roleRepo.save(transfer);
        return "Role was updated successfully";
    }
    public String deleteRole(Integer id){
        if (roleRepo.existsById(id)){
            roleRepo.deleteById(id);
            return "Role with id "+id+" deleted succssefully!";
        }else{
            return "Can not delete non-existing role!";
        }
    }
}
