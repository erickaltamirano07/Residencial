package com.residencia.gasto.Service;

import com.residencia.gasto.model.db.Gasto;
import com.residencia.gasto.model.request.GastoRequest;

import java.util.List;

public interface GastoService {
    Gasto createGasto(GastoRequest request);
    Gasto getGasto(String id);
    List<Gasto> getGastos();
    List<Gasto> getGastos(String anio, String mes);
    Gasto putGasto(String id, GastoRequest request);
    Boolean eliminarGasto(String id);
}
