package com.gyl.CrudGyl.dto;

import lombok.Data;
import java.util.List;

@Data
public class VentaRequestDto {
    private Long id_cliente;
    private List<TipoProductoRequestDto.DetalleCompraDto> productos;
}

