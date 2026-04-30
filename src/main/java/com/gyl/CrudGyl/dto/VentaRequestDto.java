
package com.gyl.CrudGyl.dto;

import java.util.List;

public record VentaRequestDto(
        Long id_Cliente,
        List<DetalleVentaRequestDto> productos
) {}