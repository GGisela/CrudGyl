package com.gyl.CrudGyl.service;
import com.gyl.CrudGyl.dto.VentaRequestDto;
import com.gyl.CrudGyl.entity.Venta;

import java.util.List;

public interface VentaService {
    Venta realizarVenta(VentaRequestDto dto);
    List<Venta> listarTodas(); // Nuevo método para ver el historial
    Double obtenerGranTotal();  // Nuevo método para la suma de todo
}