package com.residencia.empleado.data;

import com.residencia.empleado.model.db.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpleadoJpaRepository extends JpaRepository<Empleado, Long> {
    List<Empleado> findByNombre(String nombre);
}
