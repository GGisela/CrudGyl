package com.gyl.CrudGyl.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalles_venta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle_venta;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    @Column(nullable = false)
    private Double subtotal;

    // Relación con Venta: Muchos detalles pertenecen a una venta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venta", nullable = false)
    private Venta venta;

    // Relación con Producto: Muchos detalles pueden referenciar al mismo producto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;
}