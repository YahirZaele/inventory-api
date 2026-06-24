package com.yahir.inventory.service;

import com.yahir.inventory.dto.ProductDTO;
import com.yahir.inventory.exception.ResourceNotFoundException;
import com.yahir.inventory.model.Product;
import com.yahir.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// @Service = capa de lógica de negocio
// @Transactional = garantiza atomicidad en operaciones de BD
@Service
public class ProductService {

    private final ProductRepository productRepository;

    // Inyección de dependencias por constructor (mejor práctica)
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Obtener todos los productos activos
    public List<Product> getAllActiveProducts() {
        return productRepository.findByActiveTrue();
    }

    // Obtener producto por ID
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", id));
    }

    // Crear nuevo producto
    @Transactional
    public Product createProduct(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setActive(true);
        return productRepository.save(product);
    }

    // Actualizar producto
    @Transactional
    public Product updateProduct(Long id, ProductDTO dto) {
        Product product = getProductById(id);
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        return productRepository.save(product);
    }

    // Eliminar producto (soft delete — buena práctica en sistemas reales)
    @Transactional
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        product.setActive(false);
        productRepository.save(product);
    }

    // Buscar productos por nombre
    public List<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    // Productos con stock bajo
    public List<Product> getLowStockProducts(Integer threshold) {
        return productRepository.findByStockLessThan(threshold);
    }
}
