package com.residencia.gasto.Service;

import com.residencia.gasto.Facade.PresupuestoFacade;
import com.residencia.gasto.Facade.PresupuestoPutFacade;
import com.residencia.gasto.data.GastoJpaRepository;
import com.residencia.gasto.model.Presupuesto;
import com.residencia.gasto.model.db.Gasto;
import com.residencia.gasto.model.request.GastoRequest;
import com.residencia.gasto.model.request.PresupuestoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class GastoServiceImp implements GastoService{
    @Autowired
    private PresupuestoFacade presupuestoFacade;

    @Autowired
    private PresupuestoPutFacade presupuestoPutFacade;

    @Autowired
    private GastoJpaRepository repository;
    @Override
    public Gasto createGasto(GastoRequest request) {
        Presupuesto[] presupuestos= presupuestoFacade.getPresupuesto(request.getAnio());
        Presupuesto presupuesto= Arrays.stream(presupuestos).findFirst().get();
        if((presupuesto.getParcial()-request.getValor())>=0){
            presupuesto.setParcial(presupuesto.getParcial()-request.getValor());
            PresupuestoRequest presupuestoRequest= new PresupuestoRequest(presupuesto.getTotal(),
                    presupuesto.getParcial(), presupuesto.getAnio());
            presupuestoPutFacade.putPresupuesto(presupuesto.getId().toString(),presupuestoRequest);
            Gasto gasto= Gasto.builder().dia(request.getDia()).mes(request.getMes())
                    .anio(request.getAnio()).motivo(request.getMotivo())
                    .valor(request.getValor()).build();
            repository.save(gasto);
            return gasto;
        }else {
            return null;
        }

    }

    @Override
    public Gasto getGasto(String id) {
        return repository.findById(Long.valueOf(id)).orElse(null);
    }

    @Override
    public List<Gasto> getGastos() {
        List<Gasto> gasto = repository.findAll();
        return gasto.isEmpty() ? null : gasto;
    }

    @Override
    public List<Gasto> getGastos(String anio, String mes) {
        List<Gasto> gasto = repository.findGastoByAnioAndAndMes(anio,mes);
        return gasto;
    }

    @Override
    public Gasto putGasto(String id, GastoRequest request) {
        Gasto gasto= repository.findById(Long.valueOf(id)).orElse(null);
        Presupuesto[] presupuestos= presupuestoFacade.getPresupuesto(request.getAnio());
        Presupuesto presupuesto= Arrays.stream(presupuestos).findFirst().get();
        presupuesto.setParcial(presupuesto.getParcial()+gasto.getValor());
        presupuesto.setParcial(presupuesto.getParcial()-request.getValor());
        PresupuestoRequest presupuestoRequest = new PresupuestoRequest(presupuesto.getTotal(),
                presupuesto.getParcial(), presupuesto.getAnio());
        gasto.setDia(request.getDia());
        gasto.setMes(request.getMes());
        gasto.setAnio(request.getAnio());
        gasto.setMotivo(request.getMotivo());
        gasto.setValor(request.getValor());
        presupuestoPutFacade.putPresupuesto(presupuesto.getId().toString(), presupuestoRequest);
        repository.save(gasto);
        return gasto;
    }

    @Override
    public Boolean eliminarGasto(String id) {
        try{
            Gasto gasto= repository.findById(Long.valueOf(id)).orElse(null);
            Presupuesto[] presupuestos= presupuestoFacade.getPresupuesto(gasto.getAnio());
            Presupuesto presupuesto= Arrays.stream(presupuestos).findFirst().get();
            presupuesto.setParcial(presupuesto.getParcial()+gasto.getValor());
            PresupuestoRequest presupuestoRequest = new PresupuestoRequest(presupuesto.getTotal(),
                    presupuesto.getParcial(), presupuesto.getAnio());
            presupuestoPutFacade.putPresupuesto(presupuesto.getId().toString(),presupuestoRequest);
            repository.delete(gasto);
            return Boolean.TRUE;
        }catch (Exception e){
            return Boolean.FALSE;
        }
    }
}
