package com.residencia.propietario.Service;

import com.residencia.propietario.Data.PropietarioJpaRepository;
import com.residencia.propietario.model.db.Propietario;
import com.residencia.propietario.model.request.PropietarioRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PropietarioServiceImp implements PropietarioService{
    @Autowired
    private PropietarioJpaRepository repository;
    @Override
    public Propietario createPropietario(PropietarioRequest request) {
        if(request!=null){
            Propietario propietario= Propietario.builder().nombre(request.getNombre().toUpperCase())
                    .apellido(request.getApellido()).casaNumero(request.getCasaNumero())
                    .correo(request.getCorreo()).build();
            repository.save(propietario);
            return propietario;
        }else {
            return null;
        }
    }

    @Override
    public Propietario getPropietario(String id) {
        return repository.findById(Long.valueOf(id)).orElse(null);
    }


    @Override
    public List<Propietario> getPropietarios() {
        List<Propietario> propietario = repository.findAll(Sort.by("id").ascending());
        return propietario.isEmpty() ? null : propietario;
    }

    @Override
    public List<Propietario> getPropitariosNombre(String nombre) {
        List<Propietario> propietario = repository.findPropietariosByNombre(nombre.toUpperCase());
        return propietario.isEmpty() ? null : propietario;
    }

    @Override
    public Propietario putPropietario(String id, PropietarioRequest request) {

        Propietario propietario = repository.findById(Long.valueOf(id)).orElse(null);


        if (propietario == null) {
            return null;
        } else {
            propietario.setNombre(request.getNombre());
            propietario.setApellido(request.getApellido());
            propietario.setCasaNumero(request.getCasaNumero());
            propietario.setCorreo(request.getCorreo());
            repository.save(propietario);
            return propietario;
        }
    }

    @Override
    public Boolean eliminarPropietario(String id) {
        Propietario propietario = repository.findById(Long.valueOf(id)).orElse(null);
        if(propietario!= null){
            repository.delete(propietario);
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }

    }
}
