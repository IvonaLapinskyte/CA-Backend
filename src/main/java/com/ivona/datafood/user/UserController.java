package com.ivona.datafood.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/user/register")
    public ResponseEntity<String> addUser(@RequestBody UserDto user) {
        return service.addUser(user);
    }

    @PostMapping("/user/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto) {
        return service.login(loginDto);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/user/get/{username}")
    public UserDto getUser(@PathVariable("username") String username) {
        return service.getUser(username);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @PutMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @RequestBody UserDto user) {
        return service.updateUser(id, user);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @DeleteMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        return service.deleteUser(id);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/user/get/all")
    public List<UserDto> getAll() {
        return service.getAll();
    }


}
