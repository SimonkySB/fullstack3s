package com.simonky.fullstacks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simonky.fullstacks.models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
    
}
