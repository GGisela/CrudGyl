package com.gyl.CrudGyl.service.impl;

import com.gyl.CrudGyl.dto.VentaRequestDto;
import com.gyl.CrudGyl.entity.*;
import com.gyl.CrudGyl.repository.*;
import com.gyl.CrudGyl.service.VentaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        // Buscamos al cliente
        Cliente cliente = clienteRepository.findById(dto.getId_cliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setFechaVenta(LocalDate.now());
        venta.setTotal(0.0);
        venta.setDetalles(new ArrayList<>());

        double totalAcumulado = 0.0;

        for (var item : dto.getProductos()) {
            Producto producto = productoRepository.findById(item.getId_producto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado ID: " + item.getId_producto()));

            // Validación de Stock
            if (producto.getStock() < item.getCantidad()) {
                throw new RuntimeException("No hay stock suficiente para: " + producto.getNombre());
            }

            // Actualizar stock del producto
            producto.setStock(producto.getStock() - item.getCantidad());
            productoRepository.save(producto);

            // Crear el detalle
            DetalleVenta detalle = new DetalleVenta();
            detalle.setProducto(producto);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());
            detalle.setSubtotal(producto.getPrecio() * item.getCantidad());
            detalle.setVenta(venta);

            totalAcumulado += detalle.getSubtotal();
            venta.getDetalles().add(detalle);
        }

        venta.setTotal(totalAcumulado);
        return ventaRepository.save(venta);
    }
}