package com.ivona.datafood.role;

import com.ivona.datafood.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {
        @Autowired
        private RoleService service;

        @Secured("ROLE_ADMIN")
        @PostMapping("/role/add")
        public RoleDto addRole(@RequestBody RoleDto role) {
            return service.addRole(role);
        }


        @Secured("ROLE_ADMIN")
        @GetMapping("/role/get/{rolename}")
        public RoleDto getROLE(@PathVariable("rolename")String rolename){
            return service.getRole(rolename);}

        @Secured("ROLE_ADMIN")
        @PutMapping("/role/update/{id}")
        public String updateRole(@PathVariable("id")Integer id, @RequestBody RoleDto role){
            return service.updateRole(id, role);
        }

        @Secured("ROLE_ADMIN")
        @DeleteMapping("/role/delete/{id}")

        public String deleteRole(@PathVariable("id") Integer id){
            return service.deleteRole(id);
        }
        }



