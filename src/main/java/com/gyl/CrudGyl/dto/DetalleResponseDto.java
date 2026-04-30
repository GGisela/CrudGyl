package com.gyl.CrudGyl.dto;

import lombok.Data;

@Data
public class DetalleResponseDto {
    private String nombreProducto;
    private Integer cantidad;
    private Double subtotal;
}
