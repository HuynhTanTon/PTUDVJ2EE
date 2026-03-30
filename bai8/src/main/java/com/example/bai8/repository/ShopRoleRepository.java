package com.example.bai8.repository;

import com.example.bai8.entity.ShopRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopRoleRepository extends JpaRepository<ShopRole, Integer> {

    Optional<ShopRole> findByName(String name);
}
