package com.residencia.cuota.controller;
import com.residencia.cuota.model.db.Cuota;
import com.residencia.cuota.model.request.CuotaRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.residencia.cuota.service.CuotaService;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CuotaController {
    private final CuotaService service;

    @PostMapping("/cuota")
    public ResponseEntity<Cuota> createCuota(@RequestBody @Valid CuotaRequest request) {

        log.info("Creating cuota...");
        Cuota created = service.createCuota(request);

        if (created != null) {
            return ResponseEntity.ok(created);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/cuota")
    public ResponseEntity<List<Cuota>> getCuotas(@RequestParam(name = "mes", required = false) String mes, @RequestParam(name = "anio", required = false) String anio) {

        if(mes == null && anio== null){
            List<Cuota> cuota = service.getCuotas();
            if (cuota != null) {
                return ResponseEntity.ok(cuota);
            } else {
                return  ResponseEntity.ok(Collections.emptyList());
            }
        }else {
            List<Cuota> cuota = service.getCuotaMesAnio(anio, mes);
            if(cuota != null) {
                return ResponseEntity.ok(cuota);
            } else {
                return ResponseEntity.ok(Collections.emptyList());
            }
        }

    }

    @GetMapping("/cuota/{id}")
    public ResponseEntity<Cuota> getCuota(@PathVariable String id) {

        Cuota cuota = service.getCuota(id);
        if (cuota != null) {
            return ResponseEntity.ok(cuota);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/cuota/{id}")
    public ResponseEntity<Cuota> putCuota(@PathVariable String id, @RequestBody @Valid CuotaRequest request) {

        Cuota cuota = service.putCuota(id, request);
        if (cuota != null) {
            return ResponseEntity.ok(cuota);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/cuota/{id}")
    public ResponseEntity<Void> deleteCuota(@PathVariable String id) {

        Boolean removed = service.eliminarCuota(id);

        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
