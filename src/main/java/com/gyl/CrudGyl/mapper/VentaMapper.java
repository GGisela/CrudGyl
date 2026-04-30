package com.gyl.CrudGyl.mapper;

import com.gyl.CrudGyl.dto.DetalleResponseDto;
import com.gyl.CrudGyl.dto.VentaResponseDto;
import com.gyl.CrudGyl.entity.Venta;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class VentaMapper {

    public VentaResponseDto toResponseDto(Venta venta) {
        VentaResponseDto dto = new VentaResponseDto();
        dto.setId_venta(venta.getId_venta());
        dto.setFechaVenta(venta.getFechaVenta());
        dto.setTotal(venta.getTotal());

        // Unimos nombre y apellido del cliente
        dto.setNombreCliente(venta.getCliente().getNombre() + " " + venta.getCliente().getApellido());

        // Mapeamos los detalles
        dto.setItems(venta.getDetalles().stream().map(detalle -> {
            var itemDto = new DetalleResponseDto();
            itemDto.setNombreProducto(detalle.getProducto().getNombre());
            itemDto.setCantidad(detalle.getCantidad());
            itemDto.setSubtotal(detalle.getSubtotal());
            return itemDto;
        }).collect(Collectors.toList()));

        return dto;
    }
}