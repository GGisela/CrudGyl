package com.gyl.CrudGyl.controller;

import com.gyl.CrudGyl.dto.ProductResponseDto;
import com.gyl.CrudGyl.dto.ProductoRequestDto;
import com.gyl.CrudGyl.entity.Producto;
import com.gyl.CrudGyl.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 3 Eliminar (Retorna 204 No Content (en postman) si es exitoso -porque no suele devolver datos)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
    }

    // 4 Búsqueda por nombre
    // Ejemplo: /api/productos/buscar?nombre=GAMA
    @GetMapping("/buscar")
    public List<ProductResponseDto> busquedaNombre(@RequestParam String nombre) {
        return productoService.busquedaNombre(nombre);
    }

}
