package com.residencia.multas.model.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MultaRequest {

    @NotNull(message = "`motivo` cannot be null")
    @NotEmpty(message = "`motivo` cannot be empty")
    private String motivo;

    private Float total;

    private Boolean pagado;

    private Long propietario;
}
