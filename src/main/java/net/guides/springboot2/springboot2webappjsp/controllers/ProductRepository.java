package net.guides.springboot2.springboot2webappjsp.controllers;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByName(String name);
}

