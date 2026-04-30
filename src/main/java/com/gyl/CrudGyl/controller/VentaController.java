package com.gyl.CrudGyl.controller;

import com.gyl.CrudGyl.dto.VentaRequestDto;
import com.gyl.CrudGyl.dto.VentaResponseDto;
import com.gyl.CrudGyl.entity.Venta;
import com.gyl.CrudGyl.mapper.VentaMapper;
import com.gyl.CrudGyl.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private VentaMapper ventaMapper;

    // 1 Crear una nueva venta
    @PostMapping("/crear")
    public ResponseEntity<VentaResponseDto> crearVenta(@RequestBody VentaRequestDto dto) {
        Venta nuevaVenta = ventaService.realizarVenta(dto);
        return new ResponseEntity<>(ventaMapper.toResponseDto(nuevaVenta), HttpStatus.CREATED);
    }

    // 2. Obt el historial de todas las ventas
    @GetMapping("/listar")
    public ResponseEntity<List<VentaResponseDto>> listarVentas() {
        List<Venta> ventas = ventaService.listarTodas();

        // Conv la lista de Entidades a DTOs usando el Mapper
        List<VentaResponseDto> respuesta = ventas.stream()
                .map(ventaMapper::toResponseDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(respuesta);
    }
//---ultimo paso en ventas
    // 3 Obt el total acumulado de dinero en el sistema
    @GetMapping("/total-recaudado")
    public ResponseEntity<String> obtenerTotalRecaudado() {
        Double granTotal = ventaService.obtenerGranTotal();
        return ResponseEntity.ok("La facturación total acumulada es: $" + String.format("%.2f", granTotal));
    }
}