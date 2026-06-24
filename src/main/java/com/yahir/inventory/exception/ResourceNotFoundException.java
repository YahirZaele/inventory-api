package com.yahir.inventory.exception;

// Excepción cuando no se encuentra un recurso
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resource, Long id) {
        super(resource + " con ID " + id + " no encontrado");
    }
}
