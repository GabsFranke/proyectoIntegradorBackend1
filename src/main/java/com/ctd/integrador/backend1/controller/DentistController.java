package com.ctd.integrador.backend1.controller;

import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.AppointmentDTO;
import com.ctd.integrador.backend1.model.DentistDTO;
import com.ctd.integrador.backend1.service.IDentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import java.util.Set;

@RestController
@RequestMapping("/dentists")
@CrossOrigin(origins = "*")
public class DentistController {

    @Autowired
    IDentistService dentistService;

    @GetMapping
    public ResponseEntity<Set<DentistDTO>> findAll() {
        return ResponseEntity.ok(dentistService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistDTO> findById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(dentistService.findById(Long.valueOf(id)));
    }

    @GetMapping("/lastname/{lastname}")
    public ResponseEntity<DentistDTO> findByLastname(@PathVariable String lastname) throws ResourceNotFoundException {
        return ResponseEntity.ok(dentistService.findByLastName(lastname));
    }

    @GetMapping("/lastname/{lastname}/{date}")
    public ResponseEntity<Set<AppointmentDTO>> findAppointmentByDateAndDentistLastname(@PathVariable String lastname, @PathVariable String date) {
        return ResponseEntity.ok(dentistService.findAppointmentByDateAndDentistLastname(lastname, date));
    }


    @PostMapping
    public ResponseEntity<DentistDTO> addDentist(@RequestBody DentistDTO dentistDTO) {
        return ResponseEntity.ok(dentistService.addDentist(dentistDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDentist(@PathVariable Long id, @RequestBody DentistDTO dentistDTO) throws ResourceNotFoundException {
        dentistService.updateDentist(id, dentistDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDentist(@PathVariable Long id) throws ResourceNotFoundException {
        dentistService.deleteDentist(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
