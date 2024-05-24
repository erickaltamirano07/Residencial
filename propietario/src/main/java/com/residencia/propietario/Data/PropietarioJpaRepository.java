package com.residencia.propietario.Data;

import com.residencia.propietario.model.db.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropietarioJpaRepository extends JpaRepository<Propietario, Long> {
    List<Propietario> findPropietariosByNombre(String nombre);

}
