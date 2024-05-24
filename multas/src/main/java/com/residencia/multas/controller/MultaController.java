package com.residencia.multas.controller;

import com.residencia.multas.model.db.Multa;
import com.residencia.multas.model.request.MultaRequest;
import com.residencia.multas.service.MultaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MultaController {
    private final MultaService service;

    @PostMapping("/multa")
    public ResponseEntity<Multa> createMulta(@RequestBody @Valid MultaRequest request) {

        log.info("Creating multa...");
        Multa created = service.createMulta(request);

        if (created != null) {
            return ResponseEntity.ok(created);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/multa")
    public ResponseEntity<List<Multa>> getMultas(@RequestParam(name = "pagado", required = false) Boolean pagado) {

        if(pagado == null){
            List<Multa> multa = service.getMultas();
            if (multa != null) {
                return ResponseEntity.ok(multa);
            } else {
                return  ResponseEntity.ok(Collections.emptyList());
            }
        }else {
            List<Multa> multa = service.getMultaPagado(pagado);
            if(multa != null) {
                return ResponseEntity.ok(multa);
            } else {
                return ResponseEntity.ok(Collections.emptyList());
            }
        }

    }

    @GetMapping("/multa/{id}")
    public ResponseEntity<Multa> getMulta(@PathVariable String id) {

        Multa multa = service.getMulta(id);
        if (multa != null) {
            return ResponseEntity.ok(multa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/multa/{id}")
    public ResponseEntity<Multa> putMulta(@PathVariable String id, @RequestBody @Valid MultaRequest request) {

        Multa multa = service.putMulta(id, request);
        if (multa != null) {
            return ResponseEntity.ok(multa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/multa/{id}")
    public ResponseEntity<Void> deleteMulta(@PathVariable String id) {

        Boolean removed = service.eliminarMulta(id);

        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
