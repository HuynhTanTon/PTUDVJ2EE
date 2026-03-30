package com.example.bai8.repository;

import com.example.bai8.entity.ShopAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopAccountRepository extends JpaRepository<ShopAccount, Long> {

    Optional<ShopAccount> findByUsername(String username);

    boolean existsByUsername(String username);
}
