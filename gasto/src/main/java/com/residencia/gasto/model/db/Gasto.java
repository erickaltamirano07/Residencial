package com.residencia.gasto.model.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.lang.String;
import java.util.List;

@Entity
@Table(name = "Gasto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="dia")
    private String dia;
    @Column(name="mes")
    private String mes;
    @Column(name="anio")
    private String anio;
    @Column(name="motivo")
    private String motivo;
    @Column(name="valor")
    private Float valor;
}
