package com.gyl.CrudGyl.controller;

import java.util.List;
import com.gyl.CrudGyl.dto.ClienteRequestDto;
import com.gyl.CrudGyl.dto.ClienteResponseDto;
import com.gyl.CrudGyl.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:5173") // Permite que cualquier front acceda mientras desarrollas
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {


    private final ClienteService clienteService;

    // El constructor recibe un ClienteService
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDto crear(@Valid @RequestBody ClienteRequestDto dto) {
        return clienteService.crear(dto);
    }

    // Este es el metodo de Front para llenar el selector (Select)
    @GetMapping
    public List<ClienteResponseDto> listar() {
        return clienteService.listar();
    }

    @GetMapping("/{idBuscado}")
    public ClienteResponseDto buscarPorId(@PathVariable Long idBuscado) {
        return clienteService.buscarPorId(idBuscado);
    }

    @GetMapping("/nombre")
    public List<ClienteResponseDto> buscarPorNombre(@RequestParam String nombre) {
        return clienteService.buscarPorNombre(nombre);
    }

    @PutMapping("/{idBuscado}") // para actualizar
    public ClienteResponseDto actualizar(@PathVariable Long idBuscado, @Valid @RequestBody ClienteRequestDto dto) {
        return clienteService.actualizar(idBuscado, dto);
    }

    @DeleteMapping("/{idBuscado}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long idBuscado) {
        clienteService.eliminar(idBuscado);
    }
}