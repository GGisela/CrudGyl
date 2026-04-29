package com.gyl.CrudGyl.service.impl;

import com.gyl.CrudGyl.dto.TipoProductoRequestDto;
import com.gyl.CrudGyl.dto.TipoProductoResponseDto;
import com.gyl.CrudGyl.entity.TipoProducto;
import com.gyl.CrudGyl.exception.RecursosNoEncontradoException;
import com.gyl.CrudGyl.mapper.TipoProductoMapper;
import com.gyl.CrudGyl.repository.TipoProductoRepository;
import com.gyl.CrudGyl.service.TipoProductoService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TipoProductoServicioImpl implements TipoProductoService{


    private final TipoProductoRepository tipoProductoRepositorio;


    public TipoProductoServicioImpl(TipoProductoRepository tipoProductoRepositorio) {
        this.tipoProductoRepositorio = tipoProductoRepositorio;
    }

    @Override
    public TipoProductoResponseDto crear(TipoProductoRequestDto dto) {
        TipoProducto tipoProductoTraducido = TipoProductoMapper.toEntity(dto);
        TipoProducto tipoProductoGuardado = tipoProductoRepositorio.save(tipoProductoTraducido);

        return TipoProductoMapper.toResponseDto(tipoProductoGuardado);
    }

    @Override
    public List<TipoProductoResponseDto> listar() {
        return tipoProductoRepositorio.findAll().stream().map(TipoProductoMapper::toResponseDto).toList();
    }

    @Override
    public TipoProductoResponseDto buscarPorId(Long id) {
        return tipoProductoRepositorio.findById(id) // 1. Buscamos en la DB
                .map(TipoProductoMapper::toResponseDto) // 2. Convertimos a DTO si existe
                .orElseThrow(() -> new RecursosNoEncontradoException(
                        "No se encontró el tipo de producto con id: " + id)); // 3. Error si no existe
    }

    @Override
    public List<TipoProductoResponseDto> buscarPorNombre(String nombreBuscado) {
        // Cambio findBy por findByNombre
        return tipoProductoRepositorio.findByNombre(nombreBuscado)
                .stream()
                .map(TipoProductoMapper::toResponseDto)
                .toList();
    }

    @Override
    public TipoProductoResponseDto actualizar(Long idBuscado, TipoProductoRequestDto dto) {
        TipoProducto tipoProductoBuscado = tipoProductoRepositorio.findById(idBuscado)
                .orElseThrow(() -> new RecursosNoEncontradoException("No se encontró el id " + idBuscado));
        TipoProductoMapper.actualizarEntidad(tipoProductoBuscado, dto);
        TipoProducto tipoProductoActualizado = tipoProductoRepositorio.save(tipoProductoBuscado);

        return TipoProductoMapper.toResponseDto(tipoProductoActualizado);
    }

    @Override
    public void eliminar(Long idBuscado) {
        TipoProducto tipoProductoBuscado = tipoProductoRepositorio.findById(idBuscado)
                .orElseThrow(() -> new RecursosNoEncontradoException("No se encontró el id " + idBuscado));
        tipoProductoRepositorio.delete(tipoProductoBuscado);
    }
}
