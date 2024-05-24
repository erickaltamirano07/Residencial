package com.residencia.gasto.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.String;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GastoRequest {
    @NotNull(message = "`dia` cannot be null")
    @NotEmpty(message = "`dia` cannot be empty")
    private String dia;
    @NotNull(message = "`mes` cannot be null")
    @NotEmpty(message = "`mes` cannot be empty")
    private String mes;
    @NotNull(message = "`anio` cannot be null")
    @NotEmpty(message = "`anio` cannot be empty")
    private String anio;
    @NotNull(message = "`motivo` cannot be null")
    @NotEmpty(message = "`motivo` cannot be empty")
    private String motivo;
    private Float valor;
}
