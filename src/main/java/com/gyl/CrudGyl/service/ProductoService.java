package com.gyl.CrudGyl.service;

import com.gyl.CrudGyl.dto.ProductResponseDto;
import com.gyl.CrudGyl.dto.ProductoRequestDto;

import java.util.List;

public interface ProductoService {

   List<ProductResponseDto> busquedaNombre(String nombre);
   ProductResponseDto crear(ProductoRequestDto dto);
   List<ProductResponseDto> listar();
   List<ProductResponseDto> listarInactivos();
   ProductResponseDto buscarPorId(Long id);
   ProductResponseDto actualizar(Long id, ProductoRequestDto dto);
   void eliminar(Long id); // la baja lógica interna

}
