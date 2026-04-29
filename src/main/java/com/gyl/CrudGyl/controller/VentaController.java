package com.gyl.CrudGyl.controller;

import com.gyl.CrudGyl.dto.VentaRequestDto;
import com.gyl.CrudGyl.dto.VentaResponseDto;
import com.gyl.CrudGyl.entity.Venta;
import com.gyl.CrudGyl.mapper.VentaMapper;
import com.gyl.CrudGyl.service.VentaService;
import com.gyl.CrudGyl.service.impl.VentaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired private VentaService ventaService;
    @Autowired private VentaMapper ventaMapper;

    @PostMapping("/crear")
    public ResponseEntity<VentaResponseDto> crearVenta(@RequestBody VentaRequestDto dto) {
        // 1. Ejecuta la lógica
        Venta nuevaVenta = ventaService.realizarVenta(dto);
        // 2. Transforma y responde
        return ResponseEntity.ok(ventaMapper.toResponseDto(nuevaVenta));
    }
}