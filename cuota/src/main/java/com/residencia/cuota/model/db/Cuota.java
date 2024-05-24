package com.residencia.cuota.model.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cuota")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cuota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="mes")
    private String mes;
    @Column(name="anio")
    private String anio;
    @Column(name="valor")
    private Float valor;
    @Column(name="pagado")
    private Boolean pagado;
    @Column(name = "propietario")
    private Long propietario;
}
