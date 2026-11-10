package com.prograWeb3.demoCrud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prograWeb3.demoCrud.models.Categoria;
import com.prograWeb3.demoCrud.service.CategoriaService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.ResponseStatus;


@RestController 
@RequestMapping("/api/categorias")
public class CategoriaRestController {
    private final CategoriaService categoriaService;

    public CategoriaRestController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<Categoria> listar() {
        return categoriaService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria crear(@RequestBody Categoria categoria) {
        return categoriaService.crear(categoria);
    }
    
}
