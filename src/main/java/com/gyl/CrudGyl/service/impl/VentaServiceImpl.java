package com.gyl.CrudGyl.service.impl;

import com.gyl.CrudGyl.dto.VentaRequestDto;
import com.gyl.CrudGyl.entity.*;
import com.gyl.CrudGyl.repository.*;
import com.gyl.CrudGyl.service.VentaService;
import com.gyl.CrudGyl.exception.RecursosNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired private VentaRepository ventaRepository;
    @Autowired private ClienteRepository clienteRepository;
    @Autowired private ProductoRepository productoRepository;

    @Override
    @Transactional
    public Venta realizarVenta(VentaRequestDto dto) {

        Cliente cliente = clienteRepository.findById(dto.id_Cliente())
                .orElseThrow(() -> new RecursosNoEncontradoException("Cliente no existe"));

        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setFechaVenta(LocalDate.now());
        venta.setTotal(0.0);
        venta.setDetalles(new ArrayList<>());

        // ... lógica de productos
        return ventaRepository.save(venta);
    }
}