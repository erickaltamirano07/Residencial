package com.residencia.cuota.data;

import com.residencia.cuota.model.db.Cuota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuotaJpaRepository extends JpaRepository<Cuota, Long> {
    List<Cuota> findByAnioAndMes (String anio, String mes);
}
