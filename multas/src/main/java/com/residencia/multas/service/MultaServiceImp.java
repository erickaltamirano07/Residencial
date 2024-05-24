package com.residencia.multas.service;

import com.residencia.multas.data.MultaJpaRepository;
import com.residencia.multas.facade.PropietarioFacade;
import com.residencia.multas.model.Propietario;
import com.residencia.multas.model.db.Multa;
import com.residencia.multas.model.request.MultaRequest;

import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class MultaServiceImp implements MultaService{

    @Autowired
    private PropietarioFacade propietarioFacade;
    @Autowired
    private MultaJpaRepository repository;
    @Override
    public Multa createMulta(MultaRequest request) {

        Propietario propietario= propietarioFacade.getPropietario(request.getPropietario().toString());

        if(propietario==null){
            return null;
        }else {
            Date current = new Date();
            Multa multa= Multa.builder().propietario(propietario.getId()).motivo(request.getMotivo())
                    .total(request.getTotal()).pagado(request.getPagado()).Fecha(current).build();
            repository.save(multa);
            return  multa;
        }
    }

    @Override
    public Multa getMulta(String id) {
        return repository.findById(Long.valueOf(id)).orElse(null);
    }

    @Override
    public List<Multa> getMultas() {
        List<Multa> multa = repository.findAll();
        return multa.isEmpty() ? null : multa;
    }

    @Override
    public List<Multa> getMultaPagado(Boolean pagado) {
        List<Multa> multa = repository.findMultaByPagado(pagado);
        return multa;
    }

    @Override
    public Multa putMulta(String id, MultaRequest request) {
        Multa multa = repository.findById(Long.valueOf(id)).orElse(null);
        Propietario propietario= propietarioFacade.getPropietario(request.getPropietario().toString());
        if (propietario==null && multa==null) {
            return null;
        } else {
            multa.setMotivo(request.getMotivo());
            multa.setTotal(request.getTotal());
            multa.setPagado(request.getPagado());
            multa.setPropietario(request.getPropietario());
            repository.save(multa);
            return multa;
        }
    }

    @Override
    public Boolean eliminarMulta(String id) {
        Multa multa = repository.findById(Long.valueOf(id)).orElse(null);
        if(multa!= null){
            repository.delete(multa);
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }
    }
}
