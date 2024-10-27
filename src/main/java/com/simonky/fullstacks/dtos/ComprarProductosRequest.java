package com.simonky.fullstacks.dtos;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public class ComprarProductosRequest {
    
    @NotNull(message = "Productos son requeridos")
    private List<Long> productos;

    @NotNull(message = "El método de pago es requerido")
    private String metodoPago;
    
    public List<Long> getProductos() {
        return productos;
    }
    public void setProductos(List<Long> productos) {
        this.productos = productos;
    }
    public String getMetodoPago() {
        return metodoPago;
    }
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
    
}
