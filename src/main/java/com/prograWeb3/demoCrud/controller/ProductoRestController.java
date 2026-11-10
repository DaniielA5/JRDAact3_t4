package com.prograWeb3.demoCrud.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.prograWeb3.demoCrud.models.dto.ProductoDTO;
import com.prograWeb3.demoCrud.service.ProductoService;

@RestController 
@RequestMapping("/api/productos")
public class ProductoRestController {
    private final ProductoService productoService;

    public ProductoRestController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<ProductoDTO> listar() {
        return productoService.listar();
    }

    @GetMapping("/{id}")
    public ProductoDTO obtener(@PathVariable Long id) {
        return productoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoDTO crear(@RequestBody ProductoDTO dto) {
        return productoService.crear(dto);
    }

    @PutMapping("/{id}")
    public ProductoDTO actualizar(@PathVariable Long id, @RequestBody ProductoDTO dto) {
        return productoService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
    }
}