package com.gyl.CrudGyl.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public record TipoProductoRequestDto(

@NotBlank(message = "El nombre no puede estar vacío.")
String nombre,
@NotBlank(message = "La descripción no puede estar vacía.")
String descripcion
) {
    @Data
    static
    class DetalleCompraDto {
        private Long idProducto;
        private Integer cantidad;
    }
}