package com.yahir.inventory.service;

import com.yahir.inventory.dto.ProductDTO;
import com.yahir.inventory.exception.ResourceNotFoundException;
import com.yahir.inventory.model.Product;
import com.yahir.inventory.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// @ExtendWith = activa Mockito en JUnit 5
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    // @Mock = crea un objeto falso del repositorio
    @Mock
    private ProductRepository productRepository;

    // @InjectMocks = inyecta los mocks en el servicio real
    @InjectMocks
    private ProductService productService;

    private Product product1;
    private Product product2;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        product1 = new Product(1L, "Laptop Dell", "Laptop para desarrollo", 15000.00, 10, true);
        product2 = new Product(2L, "Mouse Logitech", "Mouse inalámbrico", 500.00, 25, true);
        productDTO = new ProductDTO("Teclado Mecánico", "Teclado para programadores", 1200.00, 15);
    }

    @Test
    @DisplayName("Debe retornar todos los productos activos")
    void getAllActiveProducts_ShouldReturnActiveProducts() {
        // Arrange — configura el mock
        when(productRepository.findByActiveTrue()).thenReturn(Arrays.asList(product1, product2));

        // Act — ejecuta el método
        List<Product> result = productService.getAllActiveProducts();

        // Assert — verifica el resultado
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(productRepository, times(1)).findByActiveTrue();
    }

    @Test
    @DisplayName("Debe retornar producto cuando existe el ID")
    void getProductById_WhenExists_ShouldReturnProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));

        Product result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals("Laptop Dell", result.getName());
        assertEquals(15000.00, result.getPrice());
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el ID no existe")
    void getProductById_WhenNotExists_ShouldThrowException() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            productService.getProductById(99L);
        });
    }

    @Test
    @DisplayName("Debe crear producto correctamente")
    void createProduct_ShouldSaveAndReturnProduct() {
        Product savedProduct = new Product(3L, productDTO.getName(), productDTO.getDescription(),
                productDTO.getPrice(), productDTO.getStock(), true);

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        Product result = productService.createProduct(productDTO);

        assertNotNull(result);
        assertEquals("Teclado Mecánico", result.getName());
        assertEquals(1200.00, result.getPrice());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    @DisplayName("Debe hacer soft delete — marcar como inactivo")
    void deleteProduct_ShouldMarkAsInactive() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        when(productRepository.save(any(Product.class))).thenReturn(product1);

        productService.deleteProduct(1L);

        assertFalse(product1.getActive());
        verify(productRepository, times(1)).save(product1);
    }
}
