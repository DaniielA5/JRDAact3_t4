package com.prograWeb3.demoCrud.models.dto;

import java.math.BigDecimal;

public record ProductoDTO (Long id,
        String nombre,
        BigDecimal precio,
        Integer stock,
        Long categoriaId,
        String categoriaNombre) {
    
}
