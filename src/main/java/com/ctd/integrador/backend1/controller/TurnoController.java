package com.ctd.integrador.backend1.controller;


import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.TurnoDTO;
import com.ctd.integrador.backend1.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequestMapping("/turnos")
@CrossOrigin(origins = "*")
public class TurnoController {
    @Autowired
    ITurnoService service;

    @GetMapping
    public ResponseEntity<Set<TurnoDTO>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarPorId(@PathVariable Long id) throws NoSuchElementException {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TurnoDTO> crearTurno(@RequestBody TurnoDTO turnoDTO) {
        return ResponseEntity.ok(service.agregarTurno(turnoDTO));
    }

    @PutMapping
    public ResponseEntity<?> actualizarTurno(@RequestBody TurnoDTO turnoDTO) throws ResourceNotFoundException {
        service.actualizarTurno(turnoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        service.eliminarTurno(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
