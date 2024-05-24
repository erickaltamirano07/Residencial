package com.residencia.gasto.data;

import com.residencia.gasto.model.db.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GastoJpaRepository extends JpaRepository<Gasto, Long> {
    List<Gasto> findGastoByAnioAndAndMes(String anio, String mes);
}
