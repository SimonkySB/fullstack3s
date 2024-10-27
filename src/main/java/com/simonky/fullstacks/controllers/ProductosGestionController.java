package com.simonky.fullstacks.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import com.simonky.fullstacks.exceptions.ResourceNotFoundException;
import com.simonky.fullstacks.models.Producto;
import com.simonky.fullstacks.services.ProductoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/api/gestion/productos")
public class ProductosGestionController {
    
    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public ResponseEntity<List<Producto>> listarProductos(
    ) {
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @PostMapping("/")
    public ResponseEntity<Producto> guardarProducto(@Valid @RequestBody Producto entity) {
        Producto newEntity = productoService.guardarProducto(entity);
        
        return new ResponseEntity<>(newEntity, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable("id") Long id) {
        Producto producto = productoService.obtenerProductoPorId(id).orElseThrow(() -> new ResourceNotFoundException("El producto no se encontró"));
        return ResponseEntity.ok(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> editarProducto(@PathVariable("id") Long id, @Valid @RequestBody Producto detalle) {
        Producto producto = productoService.obtenerProductoPorId(id).orElseThrow(() -> new ResourceNotFoundException("El producto no se encontró"));
        producto.setCategoria(detalle.getCategoria());
        producto.setNombre(detalle.getNombre());
        producto.setPrecio(detalle.getPrecio());
        return ResponseEntity.ok(productoService.guardarProducto(producto));
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable("id") Long id){
        productoService.obtenerProductoPorId(id).orElseThrow(() -> new ResourceNotFoundException("El producto no se encontró"));
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
