package com.prograWeb3.demoCrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prograWeb3.demoCrud.models.Categoria;

public interface CategoriaRepository  extends JpaRepository<Categoria, Long> {

    
} 