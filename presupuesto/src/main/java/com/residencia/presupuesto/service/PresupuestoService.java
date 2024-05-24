package com.residencia.presupuesto.service;

import com.residencia.presupuesto.model.db.Presupuesto;
import com.residencia.presupuesto.model.request.PresupuestoRequest;

import java.util.List;

public interface PresupuestoService {
    Presupuesto createPresupuesto(PresupuestoRequest request);

    Presupuesto getPresupuesto(String id);

    List<Presupuesto> getPresupuestos();

    List<Presupuesto> getPropitariosAnio(String anio);

    Presupuesto putPresupuesto(String id, PresupuestoRequest request);

    Boolean eliminarPresupuesto(String id);
}
