package com.ctd.integrador.backend1.controller;

import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.OdontologoDTO;
import com.ctd.integrador.backend1.model.TurnoDTO;
import com.ctd.integrador.backend1.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/odontologos")
@CrossOrigin(origins = "*")
public class OdontologoController {

    @Autowired
    IOdontologoService service;

    @GetMapping
    public ResponseEntity<Set<OdontologoDTO>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDTO> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.buscarPorId(Long.valueOf(id)));
    }

    @GetMapping("/apellido/{apellido}")
    public ResponseEntity<OdontologoDTO> buscarPorApellido(@PathVariable String apellido) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.findByLastName(apellido));
    }

    @GetMapping("/apellido/{apellido}/{fecha}")
    public ResponseEntity<Set<TurnoDTO>> buscarTurnosPorOdontologoYFecha(@PathVariable String apellido, @PathVariable String fecha) {
        return ResponseEntity.ok(service.findAppointmentByDateAndDrLastname(apellido, fecha));
    }


    @PostMapping
    public ResponseEntity<OdontologoDTO> crearOdontologo(@RequestBody OdontologoDTO odontologoDTO) {
        return ResponseEntity.ok(service.agregarOdontologo(odontologoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarOdontologo(@PathVariable Long id, @RequestBody OdontologoDTO odontologoDTO) throws ResourceNotFoundException {
        service.actualizarOdontologo(id, odontologoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        service.eliminarOdontologo(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
