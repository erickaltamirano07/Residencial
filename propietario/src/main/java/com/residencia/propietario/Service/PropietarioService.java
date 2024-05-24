package com.residencia.propietario.Service;

import com.residencia.propietario.model.db.Propietario;
import com.residencia.propietario.model.request.PropietarioRequest;

import java.util.List;

public interface PropietarioService {
    Propietario createPropietario(PropietarioRequest request);

    Propietario getPropietario(String id);

    List<Propietario> getPropietarios();

    List<Propietario> getPropitariosNombre(String nombre);

    Propietario putPropietario(String id, PropietarioRequest request);

    Boolean eliminarPropietario(String id);
}
