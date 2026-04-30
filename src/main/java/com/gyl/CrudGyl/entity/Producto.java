package com.gyl.CrudGyl.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="productos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
//PRODUCTO tiene una FK de id_tipo_producto.
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
private Long id_Producto;
    private String nombre;
    private Double precio;
    private Integer stock;

    @Column(nullable = false)
    private boolean activo = true; // Por defecto, todo producto nuevo está activo
    @ManyToOne
    @JoinColumn(name = "id_tipo_producto")
    private TipoProducto tipoProducto;
}
