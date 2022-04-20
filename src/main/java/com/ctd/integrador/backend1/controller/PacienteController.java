package com.ctd.integrador.backend1.controller;

import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.PacienteDTO;
import com.ctd.integrador.backend1.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/pacientes")
@CrossOrigin(origins = "*")
public class PacienteController {

    @Autowired
    IPacienteService service;

    @GetMapping
    public ResponseEntity<Set<PacienteDTO>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @GetMapping("dni/{dni}")
    public ResponseEntity<PacienteDTO> buscarPorDni(@PathVariable String dni) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.findPacienteByDni(dni));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException{
        if (service.buscarPorId(id) == null) {
            throw new ResourceNotFoundException("Paciente no encontrado");
        } else {
            return ResponseEntity.ok(service.buscarPorId(id));
        }
    }


    @PostMapping
    public ResponseEntity<PacienteDTO> crearPaciente(@RequestBody PacienteDTO pacienteDTO) {
        return ResponseEntity.ok(service.agregarPaciente(pacienteDTO));
    }

//    @PutMapping
//    public ResponseEntity<?> actualizarPaciente(@RequestBody PacienteDTO pacienteDTO) {
//        service.actualizarPaciente(pacienteDTO);
//        return ResponseEntity.ok(HttpStatus.OK);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPaciente(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) throws ResourceNotFoundException {
        service.actualizarPaciente(id, pacienteDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        service.eliminarPaciente(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }



}
