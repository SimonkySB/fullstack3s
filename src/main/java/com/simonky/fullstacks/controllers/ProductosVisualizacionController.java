package com.simonky.fullstacks.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simonky.fullstacks.dtos.ComprarProductosRequest;
import com.simonky.fullstacks.dtos.ComprarProductosResponse;
import com.simonky.fullstacks.exceptions.AppException;
import com.simonky.fullstacks.exceptions.ResourceNotFoundException;
import com.simonky.fullstacks.models.Producto;
import com.simonky.fullstacks.services.ProductoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping(value = "/api/public/productos")
public class ProductosVisualizacionController {
    
    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public ResponseEntity<List<Producto>> listarProductos(
        @RequestParam(required = false, defaultValue = "") String categoria,
        @RequestParam(required = false, defaultValue = "") String nombre
    ) {
        return ResponseEntity.ok(productoService.buscar(nombre, categoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable("id") Long id) {
        Producto producto = productoService.obtenerProductoPorId(id).orElseThrow(() -> new ResourceNotFoundException("El producto no se encontró"));
        return ResponseEntity.ok(producto);
    }

    @PostMapping("/comprar")
    public ResponseEntity<ComprarProductosResponse> comprar(@Valid @RequestBody ComprarProductosRequest entity) {
        
        if(entity.getProductos().isEmpty()){
            throw new AppException("No hay productos");
        }

        List<Producto> productos = new ArrayList<Producto>();

        for (Long prodId : entity.getProductos()) {
            Producto prod = productoService.obtenerProductoPorId(prodId).orElseThrow(() -> new AppException("Producto invalido") );
            productos.add(prod);
        }

        BigDecimal total = BigDecimal.ZERO;
        for (Producto p : productos) {
            total = total.add(p.getPrecio());
        }
        
        ComprarProductosResponse res = new ComprarProductosResponse();
        res.setTotal(total);
        return ResponseEntity.ok(res);
    }
    


}
