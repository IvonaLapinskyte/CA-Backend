package com.ivona.datafood.configuration;

import com.ivona.datafood.role.Role;
import com.ivona.datafood.role.RoleRepository;
import com.ivona.datafood.user.User;
import com.ivona.datafood.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

// Executes tasks when the program starts. Creates default Admin user and two default roles (ROLE_USER and ROLE_ADMIN)
@Component
public class SetupDataOnStartup implements
        ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    /*
    - "alreadySetup" variable is used because ContextRefreshedEvent may be activated several times.
    - Checks if initial privileges and roles exist and, if not, creates them.
    - Creates and saves an admin account, assigns appropriate role and privileges. (Will override existing Admin account on each startup/restart).
    */
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");
        createAdminIfNotFound();

        alreadySetup = true;
    }

    @Transactional
    void createRoleIfNotFound(String name) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            roleRepository.save(role);
        }
    }

    @Transactional
    void createAdminIfNotFound() {
        User user = userRepository.findByUsername("Admin").orElse(null);
        if (user == null) {
            user = new User();
            user.setUsername("Admin");                                                      // Username
            user.setEmail("admin@ivona.app");                                               // Email
            user.setPassword(passwordEncoder.encode("AdminIvona"));                   // Password
            user.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_ADMIN")));   // Role

            userRepository.save(user);
        }
    }
}
