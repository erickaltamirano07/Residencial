package com.residencia.cuota.service;

import com.residencia.cuota.model.db.Cuota;
import com.residencia.cuota.model.request.CuotaRequest;

import java.util.List;

public interface CuotaService {

    Cuota createCuota(CuotaRequest request);

    Cuota getCuota(String id);

    List<Cuota> getCuotas();

    List<Cuota> getCuotaMesAnio(String anio, String mes);

    Cuota putCuota( String id, CuotaRequest request);

    Boolean eliminarCuota(String id);
}
