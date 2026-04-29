package com.gyl.CrudGyl.dto;

import jakarta.validation.constraints.*;

public record ClienteRequestDto(

@NotBlank(message = "El nombre no puede estar vacío.")
String nombre,
@NotBlank(message = "El apellido no puede estar vacío.")
String apellido,
@NotBlank(message = "El correo no puede estar vacío.")
String correo,
@Min(value=10, message = "El numero tiene que tener 10 digitos o mas ")
@Max(value = 13, message = "Tienen que ser menos de 13 digitos")
@Positive(message = "El numero debe ser mayor a Cero")
@NotBlank(message = "El teléfono no puede estar vacío.")
String telefono,
@NotBlank(message = "La dirección no puede estar vacía.")
String direccion
) {}
        //

