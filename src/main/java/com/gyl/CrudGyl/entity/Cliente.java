package com.gyl.CrudGyl.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Column
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente; //pk

    @Column(nullable=false, length = 100)
    private String nombre;
    @Column(nullable=false, length = 100)
    private String apellido;

    @Column(nullable=false, length = 100)
    private String correo;

    @Column(nullable=false, length = 100)
    private String direccion;

    @Column(nullable=false, length = 20) // Agregamos el @Column que te daba error
    private String telefono;
}
