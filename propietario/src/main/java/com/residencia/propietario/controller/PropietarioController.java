package com.residencia.propietario.controller;


import com.residencia.propietario.Service.PropietarioService;
import com.residencia.propietario.model.db.Propietario;
import com.residencia.propietario.model.request.PropietarioRequest;
import com.sun.net.httpserver.Authenticator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
public class PropietarioController {

    private final PropietarioService service;


    @PostMapping("/propietario")
    public ResponseEntity<Propietario> createPropietario(@RequestBody @Valid PropietarioRequest request) {

        log.info("Creating propietario...");
        Propietario created = service.createPropietario(request);

        if (created != null) {
            return ResponseEntity.ok(created);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/propietario")
    public ResponseEntity<List<Propietario>> getPropietarios(@RequestParam(name = "nombre", required = false) String nombre ) {



            if(nombre==null){
                List<Propietario> propietario = service.getPropietarios();
                if (propietario != null) {

                    return ResponseEntity.ok(propietario);
                } else {
                    return ResponseEntity.ok(Collections.emptyList());
                }
            }else{
                List<Propietario> propietario = service.getPropitariosNombre(nombre);
                if (propietario != null) {

                    return ResponseEntity.ok(propietario);
                } else {
                    return ResponseEntity.ok(Collections.emptyList());
                }

            }


    }


    @GetMapping("/propietario/{id}")
    public ResponseEntity<Propietario> getPropietario(@PathVariable String id) {

        Propietario propietario= service.getPropietario(id);
        if (propietario!= null) {
            return ResponseEntity.ok(propietario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/propietario/{id}")
    public ResponseEntity<Propietario> putPropietario(@PathVariable String id, @RequestBody @Valid PropietarioRequest request) {

        Propietario propietario= service.putPropietario(id, request);
        if (propietario!= null) {
            return ResponseEntity.ok(propietario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/propietario/{id}")
    public ResponseEntity deletePropietario(@PathVariable String id) {

        Boolean removed = service.eliminarPropietario(id);

        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }


}
