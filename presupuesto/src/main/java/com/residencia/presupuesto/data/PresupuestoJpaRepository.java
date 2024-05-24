package com.residencia.presupuesto.data;

import com.residencia.presupuesto.model.db.Presupuesto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PresupuestoJpaRepository extends JpaRepository<Presupuesto, Long> {
    List<Presupuesto> findPresupuestoByAnio(String presupuesto);
}
