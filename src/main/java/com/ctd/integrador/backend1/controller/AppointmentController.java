package com.ctd.integrador.backend1.controller;


import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.AppointmentDTO;
import com.ctd.integrador.backend1.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    @Autowired
    IAppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<Set<AppointmentDTO>> findAll() {
        return ResponseEntity.ok(appointmentService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> findById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(appointmentService.findById(id));
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<Set<AppointmentDTO>> findAppointmentByPatientId(@PathVariable Long id){
        return ResponseEntity.ok(appointmentService.findAppointmentByPatientId(id));
    }

    @GetMapping("/dentist/{id}")
    public ResponseEntity<Set<AppointmentDTO>> findAppointmentByDentistId(@PathVariable Long id){
        return ResponseEntity.ok(appointmentService.findAppointmentByDentistId(id));
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> addAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return ResponseEntity.ok(appointmentService.addAppointment(appointmentDTO));
    }

    @PutMapping
    public ResponseEntity<?> updateAppointment(@RequestBody AppointmentDTO appointmentDTO) throws ResourceNotFoundException {
        appointmentService.updateAppointment(appointmentDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) throws ResourceNotFoundException {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Appointment deleted.");
    }



}
