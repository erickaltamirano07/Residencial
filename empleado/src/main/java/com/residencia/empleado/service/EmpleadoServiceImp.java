package com.residencia.empleado.service;

import com.residencia.empleado.data.EmpleadoJpaRepository;
import com.residencia.empleado.model.db.Empleado;
import com.residencia.empleado.model.request.EmpleadoRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class EmpleadoServiceImp implements EmpleadoService{
    @Autowired
    private EmpleadoJpaRepository repository;
    @Override
    public Empleado createEmpleado(EmpleadoRequest request) {
        if(request!=null){
            Empleado empleado= Empleado.builder().nombre(StringUtils.capitalize(request.getNombre().toLowerCase()))
                    .apellido(request.getApellido()).cargo(request.getCargo())
                    .salario(request.getSalario()).build();
            repository.save(empleado);
            return empleado;
        }else {
            return null;
        }
    }

    @Override
    public Empleado getEmpleado(String id) {
        return repository.findById(Long.valueOf(id)).orElse(null);
    }

    @Override
    public List<Empleado> getEmpleados() {
        List<Empleado> empleado = repository.findAll();
        return empleado.isEmpty() ? null : empleado;
    }

    @Override
    public List<Empleado> getPropitariosNombre(String nombre) {
        List<Empleado> empleado = repository.findByNombre(StringUtils.capitalize(nombre.toLowerCase()));
        return empleado.isEmpty() ? null : empleado;
    }

    @Override
    public Empleado putEmpleado(String id, EmpleadoRequest request) {
        Empleado empleado = repository.findById(Long.valueOf(id)).orElse(null);


        if (empleado == null) {
            return null;
        } else {
            empleado.setNombre(request.getNombre());
            empleado.setApellido(request.getApellido());
            empleado.setCargo(request.getCargo());
            empleado.setSalario(request.getSalario());
            repository.save(empleado);
            return empleado;
        }
    }

    @Override
    public Boolean eliminarEmpleado(String id) {
        Empleado empleado = repository.findById(Long.valueOf(id)).orElse(null);
        if(empleado!= null){
            repository.delete(empleado);
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }
    }
}
