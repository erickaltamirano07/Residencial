package com.residencia.gasto.Controller;

import com.residencia.gasto.Service.GastoService;
import com.residencia.gasto.model.db.Gasto;
import com.residencia.gasto.model.request.GastoRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GastoController {
    private final GastoService service;

    @PostMapping("/gasto")
    public ResponseEntity<Gasto> createGasto(@RequestBody @Valid GastoRequest request) {

        log.info("Creating gasto...");
        Gasto created = service.createGasto(request);

        if (created != null) {
            return ResponseEntity.ok(created);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/gasto")
    public ResponseEntity<List<Gasto>> getGastos(@RequestParam(name = "anio", required = false) String anio, @RequestParam(name = "mes", required = false) String mes  ) {



        if(anio==null && mes==null){
            List<Gasto> gasto = service.getGastos();
            if (gasto != null) {

                return ResponseEntity.ok(gasto);
            } else {
                return ResponseEntity.ok(Collections.emptyList());
            }
        }else{
            List<Gasto> gasto = service.getGastos(anio, mes);
            if (gasto != null) {
                return ResponseEntity.ok(gasto);
            } else {
                return ResponseEntity.ok(Collections.emptyList());
            }
        }
    }


    @GetMapping("/gasto/{id}")
    public ResponseEntity<Gasto> getGasto(@PathVariable String id) {

        Gasto gasto= service.getGasto(id);
        if (gasto!= null) {
            return ResponseEntity.ok(gasto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/gasto/{id}")
    public ResponseEntity<Gasto> putGasto(@PathVariable String id, @RequestBody @Valid GastoRequest request) {

        Gasto gasto= service.putGasto(id, request);
        if (gasto!= null) {
            return ResponseEntity.ok(gasto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/gasto/{id}")
    public ResponseEntity<String> deleteGasto(@PathVariable String id) {
        Boolean removed = service.eliminarGasto(id);
        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok("Elemento eliminado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
