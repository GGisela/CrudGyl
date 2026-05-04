package com.gyl.CrudGyl.service.impl;

import com.gyl.CrudGyl.dto.ProductoRequestDto;
import com.gyl.CrudGyl.dto.ProductResponseDto;
import com.gyl.CrudGyl.entity.Producto;
import com.gyl.CrudGyl.exception.RecursosNoEncontradoException;
import com.gyl.CrudGyl.mapper.ProductoMapper;
import com.gyl.CrudGyl.repository.ProductoRepository;
import com.gyl.CrudGyl.service.ProductoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<ProductResponseDto> busquedaNombre(String nombre){
        return productoRepository.findByNombre(nombre)
                .stream()
                .map(ProductoMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public ProductResponseDto crear(ProductoRequestDto dto) {
        Producto producto = ProductoMapper.toEntity(dto);
        // Aseguramos que al crear nazca activo
        producto.setActivo(true);
        Producto guardado = productoRepository.save(producto);
        return ProductoMapper.toResponseDto(guardado);
    }
    @Override
    public List<ProductResponseDto> listarInactivos() {
        return productoRepository.findByActivoFalse() // El método que crearemos en el Repo
                .stream()
                .map(ProductoMapper::toResponseDto)
                .toList();
    }
    @Override
    public List<ProductResponseDto> listar() {
        // Solo lista los que están activos (Baja lógica aplicada)
        return productoRepository.findByActivoTrue()
                .stream()
                .map(ProductoMapper::toResponseDto)
                .toList();
    }

    @Override
    public ProductResponseDto buscarPorId(Long id) {
        return productoRepository.findById(id)
                .map(ProductoMapper::toResponseDto)
                .orElseThrow(()-> new RecursosNoEncontradoException(
                        "No se encontró el Id " + id
                ));
    }

    @Override
    @Transactional
    public ProductResponseDto actualizar(Long id, ProductoRequestDto dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(()-> new RecursosNoEncontradoException(
                        "No se encontró el id " + id
                ));

        ProductoMapper.updateEntity(producto, dto);
        Producto guardado = productoRepository.save(producto);
        return ProductoMapper.toResponseDto(guardado);
    }

    // --- CAMBIO A BAJA LÓGICA ---
    @Override
    @Transactional
    public void eliminar(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RecursosNoEncontradoException(
                        "No se encontró el id " + id
                ));

        // En lugar de borrar (delete), desactivamos (baja lógica)
        producto.setActivo(false);
        productoRepository.save(producto);
    }
}