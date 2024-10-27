package com.simonky.fullstacks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simonky.fullstacks.models.Producto;
import com.simonky.fullstacks.repositories.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerProductoPorId(Long id){
        return productoRepository.findById(id);
    }

    public Producto guardarProducto(Producto libro){
        return productoRepository.save(libro);
    }

    public void eliminarProducto(Long id){
        productoRepository.deleteById(id);
    }
    
}
