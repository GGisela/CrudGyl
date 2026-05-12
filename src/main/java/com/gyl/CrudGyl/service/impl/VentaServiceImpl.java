package com.gyl.CrudGyl.service.impl;

import com.gyl.CrudGyl.dto.DetalleVentaRequestDto;
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
import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired private VentaRepository ventaRepository;
    @Autowired private ClienteRepository clienteRepository;
    @Autowired private ProductoRepository productoRepository;

    @Override
    @Transactional
    public Venta realizarVenta(VentaRequestDto dto) {
        // 1 Busca Cliente
        Cliente cliente = clienteRepository.findById(dto.id_Cliente())
                .orElseThrow(() -> new RecursosNoEncontradoException("El Cliente no existe"));

        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setFechaVenta(LocalDate.now());
        venta.setTotal(0.0);

        List<DetalleVenta> listaDetalles = new ArrayList<>();
        double totalAcumulado = 0.0;

        // 2 Procesa productos
        for (DetalleVentaRequestDto itemDto : dto.productos()) {
            Producto producto = productoRepository.findById(itemDto.id_producto())
                    .orElseThrow(() -> new RecursosNoEncontradoException("Producto no encontrado"));

            // Valida Stock
            if (producto.getStock() < itemDto.cantidad()) {
                throw new RuntimeException("Stock insuficiente para: " + producto.getNombre());
            }

            // Actualiza Stock
            producto.setStock(producto.getStock() - itemDto.cantidad());
            productoRepository.save(producto);

            //  Crea Detalle
            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(venta);
            detalle.setProducto(producto);
            detalle.setCantidad(itemDto.cantidad());
            detalle.setPrecioUnitario(producto.getPrecio());

            double subtotalItem = producto.getPrecio() * itemDto.cantidad();
            detalle.setSubtotal(subtotalItem);

            totalAcumulado += subtotalItem;
            listaDetalles.add(detalle);
        }

        // 4 Asigna la lista y el total a la venta
        venta.setDetalles(listaDetalles);
        venta.setTotal(totalAcumulado);

        return ventaRepository.save(venta);
    }

    // -------------------metodos para ver el total de venta

    @Override
    @Transactional(readOnly = true)
    public List<Venta> listarTodas() {
        // Trae todas las ventas guardadas en la base de datos
        return ventaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Double obtenerGranTotal() {
        // Suma el total de cada venta para darte la facturación global
        return ventaRepository.findAll()
                .stream()
                .mapToDouble(Venta::getTotal)
                .sum();
    }
}