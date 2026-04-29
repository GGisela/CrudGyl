package com.gyl.CrudGyl.service;

public interface TipoProductoService {
    TipoProductoResponseDto crear(TipoProductoRequestDto dto);

    List<TipoProductoResponseDto> listar();

    TipoProductoResponseDto buscarPorId(Long idBuscado);

    List<TipoProductoResponseDto> buscarPorNombre(String nombreBuscado);

    TipoProductoResponseDto actualizar(Long idBuscado, TipoProductoRequestDto dto);

    void eliminar(Long idBuscado);
}
