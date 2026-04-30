package com.gyl.CrudGyl.service;

import com.gyl.CrudGyl.dto.TipoProductoRequestDto;
import com.gyl.CrudGyl.dto.TipoProductoResponseDto;

import java.util.List;

public interface TipoProductoService {
    TipoProductoResponseDto crear(TipoProductoRequestDto dto);

    List<TipoProductoResponseDto> listar();

    TipoProductoResponseDto buscarPorId(Long idBuscado);

    List<TipoProductoResponseDto> buscarPorNombre(String nombreBuscado);

    TipoProductoResponseDto actualizar(Long idBuscado, TipoProductoRequestDto dto);

    void eliminar(Long idBuscado);
}
