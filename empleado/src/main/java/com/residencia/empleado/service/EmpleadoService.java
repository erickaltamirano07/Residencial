package com.residencia.empleado.service;

import com.residencia.empleado.model.db.Empleado;
import com.residencia.empleado.model.request.EmpleadoRequest;

import java.util.List;

public interface EmpleadoService {
    Empleado createEmpleado(EmpleadoRequest request);

    Empleado getEmpleado(String id);

    List<Empleado> getEmpleados();

    List<Empleado> getPropitariosNombre(String nombre);

    Empleado putEmpleado(String id, EmpleadoRequest request);

    Boolean eliminarEmpleado(String id);
}
