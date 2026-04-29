package com.gyl.CrudGyl.service;
import com.gyl.CrudGyl.dto.VentaRequestDto;
import com.gyl.CrudGyl.entity.Venta;

public interface VentaService {
    Venta realizarVenta(VentaRequestDto dto);
}