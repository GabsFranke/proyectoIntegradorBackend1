package com.ctd.integrador.backend1.controller;


import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.PatientDTO;
import com.ctd.integrador.backend1.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    @Autowired
    IPatientService patientService;

    @GetMapping
    public ResponseEntity<Set<PatientDTO>> findAll() {
        return ResponseEntity.ok(patientService.findAll());
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<PatientDTO> findByDni(@PathVariable String dni) throws ResourceNotFoundException {
        return ResponseEntity.ok(patientService.findByDni(dni));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> findById(@PathVariable Long id) throws ResourceNotFoundException{
        if (patientService.findById(id) == null) {
            throw new ResourceNotFoundException("Patient not found.");
        } else {
            return ResponseEntity.ok(patientService.findById(id));
        }
    }

    @PostMapping
    public ResponseEntity<PatientDTO> addPatient(@RequestBody PatientDTO patientDTO) {
        return ResponseEntity.ok(patientService.addPatient(patientDTO));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO) throws ResourceNotFoundException {
        patientService.updatePatient(id, patientDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) throws ResourceNotFoundException {
        patientService.deletePatient(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }



}
