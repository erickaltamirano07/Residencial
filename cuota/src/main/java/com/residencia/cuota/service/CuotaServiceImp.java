package com.residencia.cuota.service;

import com.residencia.cuota.data.CuotaJpaRepository;
import com.residencia.cuota.facade.PropietarioFacade;
import com.residencia.cuota.model.Propietario;
import com.residencia.cuota.model.db.Cuota;
import com.residencia.cuota.model.request.CuotaRequest;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CuotaServiceImp implements CuotaService{

    @Autowired
    private PropietarioFacade propietarioFacade;
    @Autowired
    private CuotaJpaRepository repository;

    @Override
    public Cuota createCuota(CuotaRequest request) {
        Propietario propietario= propietarioFacade.getPropietario(request.getPropietario().toString());

        if(propietario==null) {
            return null;
        } else {
            Cuota cuota = Cuota.builder().propietario(propietario.getId())
                    .mes(request.getMes()).anio(request.getAnio()).valor(request.getValor())
                    .pagado(request.getPagado()).build();
            repository.save(cuota);
            return cuota;
        }
    }

    @Override
    public Cuota getCuota(String id) {
        return repository.findById(Long.valueOf(id)).orElse(null);
    }

    @Override
    public List<Cuota> getCuotas() {
        List<Cuota> cuota = repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return cuota.isEmpty() ? null : cuota;
    }

    @Override
    public List<Cuota> getCuotaMesAnio(String anio, String mes) {
        List<Cuota> cuotas = repository.findByAnioAndMes(anio,mes);
        return cuotas;
    }

    @Override
    public Cuota putCuota(String id, CuotaRequest request) {

        Cuota cuota = repository.findById(Long.valueOf(id)).orElse(null);
        Propietario propietario= propietarioFacade.getPropietario(request.getPropietario().toString());
        if (propietario==null && cuota==null) {
            return null;
        } else {
            cuota.setPropietario(request.getPropietario());
            cuota.setMes(request.getMes());
            cuota.setAnio(request.getAnio());
            cuota.setValor(request.getValor());
            cuota.setPagado(request.getPagado());
            repository.save(cuota);
            return cuota;
        }
    }

    @Override
    public Boolean eliminarCuota(String id) {

        Cuota cuota = repository.findById(Long.valueOf(id)).orElse(null);
        if(cuota!= null){
            repository.delete(cuota);
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }
    }
}
