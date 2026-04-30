package com.gyl.CrudGyl.service.impl;

import com.gyl.CrudGyl.dto.ClienteRequestDto;
import com.gyl.CrudGyl.dto.ClienteResponseDto;
import com.gyl.CrudGyl.entity.Cliente;
import com.gyl.CrudGyl.exception.RecursosNoEncontradoException;
import com.gyl.CrudGyl.mapper.ClienteMapper;
import com.gyl.CrudGyl.repository.ClienteRepository;
import com.gyl.CrudGyl.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClienteServiceImple implements ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteServiceImple(ClienteRepository clienteRepositorio) {
        this.clienteRepository = clienteRepositorio;
    }

    @Override//revisar metodo!!
    public ClienteResponseDto crear(ClienteRequestDto dto) {
        Cliente clienteTraducido = ClienteMapper.toEntity(dto);
        Cliente clienteGuardado = clienteRepository.save(clienteTraducido);

        return ClienteMapper.toResponseDto(clienteGuardado);
    }

    @Override
    public List<ClienteResponseDto> listar() {
        return clienteRepository.findAll().stream().map(ClienteMapper::toResponseDto).toList();
    }

    @Override
    public ClienteResponseDto buscarPorId(Long idBuscado) {
        return clienteRepository.findById(idBuscado).map(ClienteMapper::toResponseDto)
                .orElseThrow(() -> new RecursosNoEncontradoException("No se encontró el id " + idBuscado));
    }

    @Override
    public List<ClienteResponseDto> buscarPorNombre(String nombreBuscado) {
        return clienteRepository.findByNombre(nombreBuscado).stream().map(ClienteMapper::toResponseDto).toList();
    }

    @Override
    public ClienteResponseDto actualizar(Long idBuscado, ClienteRequestDto dto) {
        Cliente clienteBuscado = clienteRepository.findById(idBuscado)
                .orElseThrow(() -> new RecursosNoEncontradoException("No se encontró el id " + idBuscado));
        ClienteMapper.actualizarEntidad(clienteBuscado, dto);
        Cliente clienteGuardado = clienteRepository.save(clienteBuscado);

        return ClienteMapper.toResponseDto(clienteGuardado);
    }

    @Override
    public void eliminar(Long idBuscado) {
        Cliente clienteBuscado = clienteRepository.findById(idBuscado)
                .orElseThrow(() -> new RecursosNoEncontradoException("No se encontró el id " + idBuscado));
        clienteRepository.delete(clienteBuscado);
    }
}
