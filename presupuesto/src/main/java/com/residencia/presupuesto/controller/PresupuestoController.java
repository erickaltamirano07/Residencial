package com.residencia.presupuesto.controller;

import com.residencia.presupuesto.model.db.Presupuesto;
import com.residencia.presupuesto.model.request.PresupuestoRequest;
import com.residencia.presupuesto.service.PresupuestoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
public class PresupuestoController {

    private final PresupuestoService service;

    @PostMapping("/presupuesto")
    public ResponseEntity<Presupuesto> createPresupuesto(@RequestBody @Valid PresupuestoRequest request) {

        log.info("Creating presupuesto...");
        Presupuesto created = service.createPresupuesto(request);

        if (created != null) {
            return ResponseEntity.ok(created);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/presupuesto")
    public ResponseEntity<List<Presupuesto>> getPresupuestos(@RequestParam(name = "anio", required = false) String anio ) {



        if(anio==null){
            List<Presupuesto> presupuesto = service.getPresupuestos();
            if (presupuesto != null) {

                return ResponseEntity.ok(presupuesto);
            } else {
                return ResponseEntity.ok(Collections.emptyList());
            }
        }else{
            List<Presupuesto> presupuesto = service.getPropitariosAnio(anio);
            if (presupuesto != null) {
                return ResponseEntity.ok(presupuesto);
            } else {
                return ResponseEntity.ok(Collections.emptyList());
            }
        }
    }


    @GetMapping("/presupuesto/{id}")
    public ResponseEntity<Presupuesto> getPresupuesto(@PathVariable String id) {

        Presupuesto presupuesto= service.getPresupuesto(id);
        if (presupuesto!= null) {
            return ResponseEntity.ok(presupuesto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/presupuesto/{id}")
    public ResponseEntity<Presupuesto> putPresupuesto(@PathVariable String id, @RequestBody @Valid PresupuestoRequest request) {

        Presupuesto presupuesto= service.putPresupuesto(id, request);
        if (presupuesto!= null) {
            return ResponseEntity.ok(presupuesto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/presupuesto/{id}")
    public ResponseEntity<String> deletePresupuesto(@PathVariable String id) {

        Boolean removed = service.eliminarPresupuesto(id);

        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok("Elemento eliminado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
