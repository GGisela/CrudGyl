package com.gyl.CrudGyl.controller;

import com.gyl.CrudGyl.dto.ProductResponseDto;
import com.gyl.CrudGyl.dto.ProductoRequestDto;
import com.gyl.CrudGyl.entity.Producto;
import com.gyl.CrudGyl.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173") // <-- AGREGA ESTO para FRONT
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDto crear(@Valid @RequestBody ProductoRequestDto dto) {
        return productoService.crear(dto);
    }

    @GetMapping
    public List<ProductResponseDto> listar(){
        return productoService.listar();
    }
    @GetMapping("/inactivos")
    @Operation(summary = "Listar productos dados de baja", description = "Retorna INACTIVOS (bandera de activo es false)")
    public List<ProductResponseDto> listarInactivos() {
        return productoService.listarInactivos();
    }
//@PathVariable para recursos específicos (como un ID)
    //@RequestParam para filtros de búsqueda


// 1 Buscar por ID
@GetMapping("/{id}")
public ProductResponseDto buscarPorId(@PathVariable Long id) {
    return productoService.buscarPorId(id);
}

    // 2 Actualizar (Usa PUT para reemplazo total del recurso)
    @PutMapping("/{id}")
    public ProductResponseDto actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequestDto dto) {
        return productoService.actualizar(id, dto);
    }

    /* 3 Eliminar (Retorna 204 No Content (en postman) si es exitoso -porque no suele devolver datos)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
    }*/
    @PatchMapping("/{id}/baja")
    public ResponseEntity<String> darDeBaja(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.ok("Producto desactivado correctamente.");
    }

    // 4 Búsqueda por nombre
    // Ejemplo: /api/productos/buscar?nombre=GAMA
    @GetMapping("/buscar")
    public List<ProductResponseDto> busquedaNombre(@RequestParam String nombre) {
        return productoService.busquedaNombre(nombre);
    }

}
