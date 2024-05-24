package com.residencia.empleado.model.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoRequest {

    @NotNull(message = "`nombre` cannot be null")
    @NotEmpty(message = "`nombre` cannot be empty")
    private String nombre;
    @NotNull(message = "`apellido` cannot be null")
    @NotEmpty(message = "`apellido` cannot be empty")
    private String apellido;
    @NotNull(message = "`cargo` cannot be null")
    @NotEmpty(message = "`cargo` cannot be empty")
    private String cargo;
    private Float salario;
}
