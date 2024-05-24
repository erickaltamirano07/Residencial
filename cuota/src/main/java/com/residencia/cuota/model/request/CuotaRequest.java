package com.residencia.cuota.model.request;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CuotaRequest {

    @NotNull(message = "`mes` cannot be null")
    @NotEmpty(message = "`mes` cannot be empty")
    private String mes;
    @NotNull(message = "`año` cannot be null")
    @NotEmpty(message = "`año` cannot be empty")
    private String anio;
    private Float valor;
    private Boolean pagado;
    private Long propietario;
}
