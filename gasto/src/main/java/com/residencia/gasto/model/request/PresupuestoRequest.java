package com.residencia.gasto.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PresupuestoRequest {
    private Float total;
    private Float parcial;
    private String anio;
}
