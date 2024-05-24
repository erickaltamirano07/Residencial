package com.residencia.gasto.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class Presupuesto {

    private Long id;
    private Float total;
    private Float parcial;
    private String anio;
}
