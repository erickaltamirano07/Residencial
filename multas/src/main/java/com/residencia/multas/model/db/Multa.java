package com.residencia.multas.model.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "multa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Multa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "Motivo")
    private String motivo;

    @Column(name = "total")
    private Float total;

    @Column(name = "Pagado")
    private Boolean pagado;

    @Column(name = "Fecha")
    private Date Fecha;

    @Column(name = "propietario")
    private Long propietario;

}
