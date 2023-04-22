package com.ivona.datafood.user;

import com.ivona.datafood.configuration.JwtUtils;
import com.ivona.datafood.configuration.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;
    @Autowired
    UserMapper userMapper;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;


    // Registration
    public ResponseEntity<String> addUser(UserDto user) {
        if (user == null) {
            return ResponseEntity.badRequest().body("Something went wrong! Please try again.");
        } else if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("User with this username already exists!");
        } else if (userRepo.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("User with this email already exists!");
        } else if (!user.getPassword().equals(user.getRepeatPassword())) {
            return ResponseEntity.badRequest().body("Passwords don't match!");
        }

        userRepo.save(userMapper.fromDto(user));
        return ResponseEntity.ok().body("User successfully saved. Please log in!");
    }

    // Log in
    public ResponseEntity<Object> login(LoginDto loginDto) {
        String fieldType;
        User user;

        // Check if logging in with username or email
        if (loginDto.getUnameOrEmail().contains("@")) {
            user = userRepo.findByEmail(loginDto.getUnameOrEmail());
            fieldType = "email ";
        } else {
            user = userRepo.findByUsername(loginDto.getUnameOrEmail()).orElse(null);
            fieldType = "username ";
        }

        // Authenticate, send JWT token
        if (user == null) {
            return ResponseEntity.badRequest().body("User with " + fieldType +" "+ loginDto.getUnameOrEmail() + "was not found!");
        } else {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String jwtToken = jwtUtils.generateJwtToken(userDetails);

            String response = "Logged in!";
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtToken).header("Access-Control-Expose-Headers","Authorization").body(response);
        }
    }


    public UserDto getUser(String username) {
        if (username == null) {
            return null;
        }

        User user = userRepo.findByUsername(username).orElse(null);

        if (user == null) {
            return null;
        }

        return userMapper.toDto(user);

    }



    public String updateUser(Integer id, UserDto userDto) {

        if (id == null || userDto == null) {
            return null;
        }

        User originalUser = userRepo.findById(id).orElse(null);

        if (originalUser == null) {
            return "User with id "+id+" was not found!";
        }

        User changedUser = userMapper.fromDto(userDto);
        changedUser.setId(id);
        changedUser.setEmail(originalUser.getEmail());
        userRepo.save(changedUser);

        return "User was updated successfully!";


    }

    public String deleteUser(Integer id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return "User with id "+id+" deleted successfully!";
        } else {
            return "Can not delete non-existing user!";
        }
    }

    public List<UserDto> getAll() {

        List<User> users = (List<User>) userRepo.findAll();
        List<UserDto> dtoUsers = new ArrayList<>();

        users.forEach(user -> dtoUsers.add(userMapper.toDto(user)));

        return dtoUsers;
    }

}
