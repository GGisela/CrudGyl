package com.gyl.CrudGyl.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class VentaResponseDto {
    private Long id_venta;
    private LocalDate fechaVenta;
    private String nombreCliente;
    private Double total;
    private List<DetalleResponseDto> items;
}

