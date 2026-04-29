package com.gyl.CrudGyl.service.impl;

import com.gyl.CrudGyl.exception.RecursosNoEncontradoException;
import com.gyl.CrudGyl.mapper.ClienteMapper;
import com.gyl.CrudGyl.repository.ClienteRepository;

public class ClienteServiceImple {
    private final ClienteRepository clienteRepositorio;

    public ClienteServicioImpl(ClienteRepository clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    @Override//revisar metodo!!
    public ClienteResponseDto crear(ClienteRequestDto dto) {
        Cliente clienteTraducido = ClienteMapper.toEntity(dto);
        Cliente clienteGuardado = clienteRepositorio.save(clienteTraducido);

        return ClienteMapper.toResponseDto(clienteGuardado);
    }

    @Override
    public List<ClienteResponseDto> listar() {
        return clienteRepositorio.findAll().stream().map(ClienteMapper::toResponseDto).toList();
    }

    @Override
    public ClienteResponseDto buscarPorId(Long idBuscado) {
        return clienteRepositorio.findById(idBuscado).map(ClienteMapper::toResponseDto)
                .orElseThrow(() -> new ExcepcionRecursoNoEncontrado("No se encontró el id " + idBuscado));
    }

    @Override
    public List<ClienteResponseDto> buscarPorNombre(String nombreBuscado) {
        return clienteRepositorio.findByNombre(nombreBuscado).stream().map(ClienteMapper::toResponseDto).toList();
    }

    @Override
    public ClienteResponseDto actualizar(Long idBuscado, ClienteRequestDto dto) {
        Cliente clienteBuscado = clienteRepositorio.findById(idBuscado)
                .orElseThrow(() -> new RecursosNoEncontradoException("No se encontró el id " + idBuscado));
        ClienteMapper.actualizarEntidad(clienteBuscado, dto);
        Cliente clienteGuardado = clienteRepositorio.save(clienteBuscado);

        return ClienteMapper.toResponseDto(clienteGuardado);
    }

    @Override
    public void eliminar(Long idBuscado) {
        Cliente clienteBuscado = clienteRepositorio.findById(idBuscado)
                .orElseThrow(() -> new RecursosNoEncontradoException("No se encontró el id " + idBuscado));
        clienteRepositorio.delete(clienteBuscado);
    }
}
