package com.ctd.integrador.backend1.controller;


import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.DomicilioDTO;

import com.ctd.integrador.backend1.service.IDomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/domicilios")
@CrossOrigin(origins = "*")
public class DomicilioController {

    @Autowired
    IDomicilioService service;

    @GetMapping
    public ResponseEntity<Set<DomicilioDTO>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DomicilioDTO> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<DomicilioDTO> crearDomicilio(@RequestBody DomicilioDTO domicilioDTO) {
        return ResponseEntity.ok(service.agregarDomicilio(domicilioDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDomicilio(@PathVariable Long id, @RequestBody DomicilioDTO domicilioDTO) throws ResourceNotFoundException {
        service.actualizarDomicilio(id, domicilioDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarDomicilio(@PathVariable Long id) throws ResourceNotFoundException {
        service.eliminarDomicilio(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
