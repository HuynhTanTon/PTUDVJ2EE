package com.example.bai8.service;

import com.example.bai8.entity.ShopAccount;
import com.example.bai8.entity.ShopRole;
import com.example.bai8.repository.ShopAccountRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

/** Load user từ DB cho Spring Security (username → authorities theo role). */
@Service
@Profile("!test")
public class AccountUserDetailsService implements UserDetailsService {

    private final ShopAccountRepository shopAccountRepository;

    public AccountUserDetailsService(ShopAccountRepository shopAccountRepository) {
        this.shopAccountRepository = shopAccountRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ShopAccount acc = shopAccountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy: " + username));

        var authorities = acc.getRoles().stream()
                .map(ShopRole::getName)
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                .collect(Collectors.toSet());

        return new User(acc.getUsername(), acc.getPassword(), authorities);
    }
}
