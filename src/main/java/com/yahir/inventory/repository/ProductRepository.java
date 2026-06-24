package com.yahir.inventory.repository;

import com.yahir.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Buscar productos activos
    List<Product> findByActiveTrue();

    // Buscar por nombre (contiene, ignora mayúsculas)
    List<Product> findByNameContainingIgnoreCase(String name);

    // Buscar productos con stock menor a un valor
    List<Product> findByStockLessThan(Integer stock);
}
