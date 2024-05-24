package com.residencia.multas.service;

import com.residencia.multas.model.db.Multa;
import com.residencia.multas.model.request.MultaRequest;

import java.util.List;

public interface MultaService {
    Multa createMulta(MultaRequest request);

    Multa getMulta(String id);

    List<Multa> getMultas();

    List<Multa> getMultaPagado(Boolean pagado);

    Multa putMulta( String id, MultaRequest request);

    Boolean eliminarMulta(String id);

}
