package com.prograWeb3.demoCrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prograWeb3.demoCrud.models.Producto;
import java.util.List;


public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoriaId(Long categoriaId);
    
}