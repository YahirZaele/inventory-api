package com.yahir.inventory.controller;

import com.yahir.inventory.dto.ProductDTO;
import com.yahir.inventory.model.Product;
import com.yahir.inventory.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Productos", description = "CRUD de gestión de inventario")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET /api/products → 200 OK
    @GetMapping
    @Operation(summary = "Obtener todos los productos activos")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllActiveProducts());
    }

    // GET /api/products/{id} → 200 OK | 404 Not Found
    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // POST /api/products → 201 Created
    @PostMapping
    @Operation(summary = "Crear nuevo producto")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDTO dto) {
        Product created = productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/products/{id} → 200 OK | 404 Not Found
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto existente")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductDTO dto) {
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }

    // DELETE /api/products/{id} → 204 No Content | 404 Not Found
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto (soft delete)")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/products/search?name=laptop → 200 OK
    @GetMapping("/search")
    @Operation(summary = "Buscar productos por nombre")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String name) {
        return ResponseEntity.ok(productService.searchByName(name));
    }

    // GET /api/products/low-stock?threshold=5 → 200 OK
    @GetMapping("/low-stock")
    @Operation(summary = "Productos con stock bajo")
    public ResponseEntity<List<Product>> getLowStock(
            @RequestParam(defaultValue = "5") Integer threshold) {
        return ResponseEntity.ok(productService.getLowStockProducts(threshold));
    }
}
