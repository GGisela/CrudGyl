package com.gyl.CrudGyl.controller;


import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.gyl.CrudGyl.dto.ClienteRequestDto;
import com.gyl.CrudGyl.dto.ClienteResponseDto;
import com.gyl.CrudGyl.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteServicio;

    public ClienteController(ClienteService clienteServicio) {this.clienteServicio = clienteServicio;}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDto crear(@Valid @RequestBody ClienteRequestDto dto) {return clienteServicio.crear(dto);}

    @GetMapping
    public List<ClienteResponseDto> listar() {return clienteServicio.listar();}

    @GetMapping("/{idBuscado}")
    public ClienteResponseDto buscarPorId(@PathVariable Long idBuscado) {
        return clienteServicio.buscarPorId(idBuscado);
    }

    @GetMapping("/nombre")
    public List<ClienteResponseDto> buscarPorNombre(@RequestParam String nombre) {
        return clienteServicio.buscarPorNombre(nombre);
    }

    @PostMapping("/{idBuscado}")
    public ClienteResponseDto actualizar(@PathVariable Long idBuscado, @Valid @RequestBody ClienteRequestDto dto) {
        return clienteServicio.actualizar(idBuscado, dto);
    }

    @DeleteMapping("/{idBuscado}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long idBuscado) {clienteServicio.eliminar(idBuscado);}
}