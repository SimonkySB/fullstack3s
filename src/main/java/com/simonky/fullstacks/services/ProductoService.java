package com.simonky.fullstacks.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Producto> buscar(String nombre , String categoria){
        List<Producto> productos =  productoRepository.findAll();

        if (!categoria.isEmpty()) {
            productos = productos.stream()
                .filter(producto -> producto.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
        }
        
        if (!nombre.isEmpty()) {
            productos = productos.stream()
                    .filter(producto -> producto.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return productos;
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
