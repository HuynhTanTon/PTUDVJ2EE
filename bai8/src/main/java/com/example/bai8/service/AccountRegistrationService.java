package com.example.bai8.service;

import com.example.bai8.entity.ShopAccount;
import com.example.bai8.entity.ShopRole;
import com.example.bai8.repository.ShopAccountRepository;
import com.example.bai8.repository.ShopRoleRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Đăng ký tài khoản mới với role USER. */
@Service
@Profile("!test")
public class AccountRegistrationService {

    private final ShopAccountRepository shopAccountRepository;
    private final ShopRoleRepository shopRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountRegistrationService(ShopAccountRepository shopAccountRepository,
                                      ShopRoleRepository shopRoleRepository,
                                      PasswordEncoder passwordEncoder) {
        this.shopAccountRepository = shopAccountRepository;
        this.shopRoleRepository = shopRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(String username, String rawPassword) {
        if (shopAccountRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username đã tồn tại");
        }
        ShopRole userRole = shopRoleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Thiếu role USER trong DB"));

        ShopAccount acc = ShopAccount.builder()
                .username(username)
                .password(passwordEncoder.encode(rawPassword))
                .build();
        acc.getRoles().add(userRole);
        shopAccountRepository.save(acc);
    }
}
