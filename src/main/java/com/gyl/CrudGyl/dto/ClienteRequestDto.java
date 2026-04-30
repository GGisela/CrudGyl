package com.gyl.CrudGyl.dto;

import jakarta.validation.constraints.*;

public record ClienteRequestDto(

@NotBlank(message = "El nombre no puede estar vacío.")
String nombre,
@NotBlank(message = "El apellido no puede estar vacío.")
String apellido,
@NotBlank(message = "El correo no puede estar vacío.")
String correo,
@NotBlank(message = "La dirección no puede estar vacía.")
String direccion,
@NotBlank(message = "El teléfono no puede estar vacío.")
String telefono

) {}
        //

