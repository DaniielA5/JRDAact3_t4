package com.prograWeb3.demoCrud.service;

import java.util.List;

import org.springframework.http.HttpStatus;
 import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.prograWeb3.demoCrud.models.Categoria;
import com.prograWeb3.demoCrud.repository.CategoriaRepository;

@Service 
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    public Categoria crear(Categoria categoria) {
        if (categoria.getNombre() == null || categoria.getNombre().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "EL nombre es obligatorio");
        }
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

}
