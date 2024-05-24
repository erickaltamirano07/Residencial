package com.residencia.presupuesto.service;

import com.residencia.presupuesto.data.PresupuestoJpaRepository;
import com.residencia.presupuesto.model.db.Presupuesto;
import com.residencia.presupuesto.model.request.PresupuestoRequest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PresupuestoServiceImp implements PresupuestoService{
    @Autowired
    private PresupuestoJpaRepository repository;
    @Override
    public Presupuesto createPresupuesto(PresupuestoRequest request) {
        if(request!=null){
            Presupuesto presupuesto= Presupuesto.builder().total(request.getTotal())
                            .parcial(request.getParcial()).anio(request.getAnio()).build();
            repository.save(presupuesto);
            return presupuesto;
        }else {
            return null;
        }
    }

    @Override
    public Presupuesto getPresupuesto(String id) {
        return repository.findById(Long.valueOf(id)).orElse(null);
    }

    @Override
    public List<Presupuesto> getPresupuestos() {
        List<Presupuesto> presupuesto = repository.findAll();
        return presupuesto.isEmpty() ? null : presupuesto;
    }

    @Override
    public List<Presupuesto> getPropitariosAnio(String anio) {
        List<Presupuesto> presupuesto = repository.findPresupuestoByAnio(anio);
        return presupuesto.isEmpty() ? null : presupuesto;
    }

    @Override
    public Presupuesto putPresupuesto(String id, PresupuestoRequest request) {
        Presupuesto presupuesto = repository.findById(Long.valueOf(id)).orElse(null);
        if (presupuesto == null) {
            return null;
        } else {
            presupuesto.setTotal(request.getTotal());
            presupuesto.setParcial(request.getParcial());
            presupuesto.setAnio(request.getAnio());
            repository.save(presupuesto);
            return presupuesto;
        }
    }

    @Override
    public Boolean eliminarPresupuesto(String id) {
        Presupuesto presupuesto = repository.findById(Long.valueOf(id)).orElse(null);
        if(presupuesto!= null){
            repository.delete(presupuesto);
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }
    }
}
