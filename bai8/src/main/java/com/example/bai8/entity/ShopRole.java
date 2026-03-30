package com.example.bai8.entity;

import jakarta.persistence.*;
import lombok.*;

/** Vai trò: USER (mua hàng), ADMIN (quản trị). */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shop_roles")
public class ShopRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;
}
