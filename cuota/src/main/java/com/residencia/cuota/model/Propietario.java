package com.residencia.cuota.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Propietario {

    private Long id;
    private String nombre;
    private String apellido;
    private Long casaNumero;
    private String correo;
}
