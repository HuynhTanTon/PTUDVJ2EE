package com.example.bai8.config;

import com.example.bai8.entity.ShopAccount;
import com.example.bai8.entity.ShopRole;
import com.example.bai8.repository.ShopAccountRepository;
import com.example.bai8.repository.ShopRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Tạo role USER, ADMIN và tài khoản mẫu (chỉ khi DB trống role).
 */
@Configuration
@Profile("!test")
public class SecurityDataInitializer {

    @Bean
    @Order(1)
    CommandLineRunner seedRolesAndAccounts(ShopRoleRepository roleRepository,
                                           ShopAccountRepository accountRepository,
                                           PasswordEncoder passwordEncoder) {
        return args -> {
            if (roleRepository.count() > 0) {
                return;
            }
            ShopRole userRole = roleRepository.save(new ShopRole(null, "USER"));
            ShopRole adminRole = roleRepository.save(new ShopRole(null, "ADMIN"));

            // user / 123456 — khách mua hàng
            ShopAccount user = ShopAccount.builder()
                    .username("user")
                    .password(passwordEncoder.encode("123456"))
                    .build();
            user.getRoles().add(userRole);
            accountRepository.save(user);

            // admin / 123456 — quản trị + mua hàng (cả USER + ADMIN)
            ShopAccount admin = ShopAccount.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("123456"))
                    .build();
            admin.getRoles().add(userRole);
            admin.getRoles().add(adminRole);
            accountRepository.save(admin);
        };
    }
}
