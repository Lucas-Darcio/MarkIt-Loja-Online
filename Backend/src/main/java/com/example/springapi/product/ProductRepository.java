package com.example.springapi.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    // "extends JpaRepository<Product, UUID>" Só de estender, já ganha save(), findAll(), findById(), etc.
    boolean existsByName(String name);
}