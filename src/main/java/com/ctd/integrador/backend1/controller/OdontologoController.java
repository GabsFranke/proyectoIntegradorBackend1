package com.ctd.integrador.backend1.controller;

import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.OdontologoDTO;
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

    @GetMapping("/apellido/{apellido}")
    public ResponseEntity<OdontologoDTO> buscarPorApellido(@PathVariable String apellido) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.findByLastName(apellido));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDTO> buscarPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<OdontologoDTO> crearOdontologo(@RequestBody OdontologoDTO odontologoDTO) {
        return ResponseEntity.ok(service.agregarOdontologo(odontologoDTO));
    }

//    @PutMapping
//    public ResponseEntity<?> actualizarOdontologo(@RequestBody OdontologoDTO odontologoDTO) {
//        service.actualizarOdontologo(odontologoDTO);
//        return ResponseEntity.ok(HttpStatus.OK);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarOdontologo(@PathVariable Integer id, @RequestBody OdontologoDTO odontologoDTO) throws ResourceNotFoundException {
        service.actualizarOdontologo(id, odontologoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarOdontologo(@PathVariable Integer id) throws ResourceNotFoundException {
        service.eliminarOdontologo(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
