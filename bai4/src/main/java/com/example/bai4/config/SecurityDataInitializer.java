package com.example.bai4.config;

import com.example.bai4.model.Account;
import com.example.bai4.model.Role;
import com.example.bai4.repository.AccountRepository;
import com.example.bai4.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
public class SecurityDataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public SecurityDataInitializer(RoleRepository roleRepository,
                                   AccountRepository accountRepository,
                                   PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {
        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName("ADMIN");
                    return roleRepository.save(role);
                });

        Role userRole = roleRepository.findByName("USER")
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName("USER");
                    return roleRepository.save(role);
                });

        Account admin = accountRepository.findByUsername("admin").orElseGet(Account::new);
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("123456"));
        admin.setEnabled(true);
        admin.setRoles(new HashSet<>(Set.of(adminRole, userRole)));
        accountRepository.save(admin);

        Account user = accountRepository.findByUsername("user").orElseGet(Account::new);
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setEnabled(true);
        user.setRoles(new HashSet<>(Set.of(userRole)));
        accountRepository.save(user);
    }
}
