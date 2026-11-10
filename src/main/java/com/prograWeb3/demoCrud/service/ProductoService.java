package com.prograWeb3.demoCrud.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.prograWeb3.demoCrud.models.Categoria;
import com.prograWeb3.demoCrud.models.Producto;
import com.prograWeb3.demoCrud.models.dto.ProductoDTO;
import com.prograWeb3.demoCrud.repository.CategoriaRepository;
import com.prograWeb3.demoCrud.repository.ProductoRepository;

@Service 
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;

    }

    @Transactional(readOnly = true )
    public List<ProductoDTO> listar() {
        return productoRepository.findAll().stream().map(this::toDTO).toList();

    }

    @Transactional(readOnly = true)
    public ProductoDTO obtener(Long id) {
        return toDTO(buscar(id));
    }

    @Transactional
    public ProductoDTO crear(ProductoDTO dto) {
        Producto producto = new Producto();
        aplicar(producto, dto);
        return toDTO(productoRepository.save(producto));
    }

    @Transactional
    public ProductoDTO actualizar(Long id, ProductoDTO dto) {
        Producto producto = buscar(id);
        aplicar(producto, dto);
        return toDTO(productoRepository.save(producto));
    }

    @Transactional
    public void eliminar(Long id) {
        productoRepository.delete(buscar(id));
    }

    // ---- métodos privados ----

    private Producto buscar(Long id) {
        return productoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto " + id + " no existe"));
    }

    private void aplicar(Producto producto, ProductoDTO dto) {
        if (dto.nombre() == null || dto.nombre().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre es obligatorio");
        }
        if (dto.precio() == null || dto.precio().compareTo(BigDecimal.ZERO) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El precio debe ser 0 o mayor");
        }
        if (dto.stock() != null && dto.stock() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El stock no puede ser negativo");
        }
        if (dto.categoriaId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "categoriaId es obligatorio");
        }
        Categoria categoria = categoriaRepository.findById(dto.categoriaId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "La categoría " + dto.categoriaId() + " no existe"));

        producto.setNombre(dto.nombre().trim());
        producto.setPrecio(dto.precio());
        producto.setStock(dto.stock() == null ? 0 : dto.stock());
        producto.setCategoria(categoria);
    }

    private ProductoDTO toDTO(Producto p) {
        return new ProductoDTO(p.getId(), p.getNombre(), p.getPrecio(), p.getStock(),
                p.getCategoria().getId(), p.getCategoria().getNombre());
    }
}