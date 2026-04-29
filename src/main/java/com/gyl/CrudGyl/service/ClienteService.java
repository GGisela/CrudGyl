package com.gyl.CrudGyl.service;

public interface ClienteService {
    //List<ClienteDTO> listarTodos();
    ClienteResponseDto crear(ClienteRequestDto dto);

    List<ClienteResponseDto> listar();

    ClienteResponseDto buscarPorId(Long idBuscado);

    List<ClienteResponseDto> buscarPorNombre(String nombreBuscado);

    ClienteResponseDto actualizar(Long idBuscado, ClienteRequestDto dto);

    void eliminar(Long idBuscado);
}
