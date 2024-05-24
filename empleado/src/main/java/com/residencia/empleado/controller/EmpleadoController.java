package com.residencia.empleado.controller;

import com.residencia.empleado.model.db.Empleado;
import com.residencia.empleado.model.request.EmpleadoRequest;
import com.residencia.empleado.service.EmpleadoService;
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
public class EmpleadoController {

    private final EmpleadoService service;


    @PostMapping("/empleado")
    public ResponseEntity<Empleado> createEmpleado(@RequestBody @Valid EmpleadoRequest request) {

        log.info("Creating empleado...");
        Empleado created = service.createEmpleado(request);

        if (created != null) {
            return ResponseEntity.ok(created);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/empleado")
    public ResponseEntity<List<Empleado>> getEmpleados(@RequestParam(name = "nombre", required = false) String nombre ) {



        if(nombre==null){
            List<Empleado> empleado = service.getEmpleados();
            if (empleado != null) {

                return ResponseEntity.ok(empleado);
            } else {
                return ResponseEntity.ok(Collections.emptyList());
            }
        }else{
            List<Empleado> empleado = service.getPropitariosNombre(nombre);
            if (empleado != null) {

                return ResponseEntity.ok(empleado);
            } else {
                return ResponseEntity.ok(Collections.emptyList());
            }

        }


    }


    @GetMapping("/empleado/{id}")
    public ResponseEntity<Empleado> getEmpleado(@PathVariable String id) {

        Empleado empleado= service.getEmpleado(id);
        if (empleado!= null) {
            return ResponseEntity.ok(empleado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/empleado/{id}")
    public ResponseEntity<Empleado> putEmpleado(@PathVariable String id, @RequestBody @Valid EmpleadoRequest request) {

        Empleado empleado= service.putEmpleado(id, request);
        if (empleado!= null) {
            return ResponseEntity.ok(empleado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/empleado/{id}")
    public ResponseEntity<String> deleteEmpleado(@PathVariable String id) {

        Boolean removed = service.eliminarEmpleado(id);

        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok("Elemento eliminado");
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
