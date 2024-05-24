package com.residencia.multas.data;

import com.residencia.multas.model.db.Multa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MultaJpaRepository extends JpaRepository<Multa, Long> {
    List<Multa> findMultaByPagado(Boolean pagado);
}
