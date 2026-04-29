package com.gyl.CrudGyl.service;

import com.gyl.CrudGyl.dto.ClienteRequestDto;
import com.gyl.CrudGyl.dto.ClienteResponseDto;

import java.util.List;

public interface ClienteService {
    //List<ClienteDTO> listarTodos();
    ClienteResponseDto crear(ClienteRequestDto dto);

    List<ClienteResponseDto> listar();

    ClienteResponseDto buscarPorId(Long idBuscado);

    List<ClienteResponseDto> buscarPorNombre(String nombreBuscado);

    ClienteResponseDto actualizar(Long idBuscado, ClienteRequestDto dto);

    void eliminar(Long idBuscado);
}
