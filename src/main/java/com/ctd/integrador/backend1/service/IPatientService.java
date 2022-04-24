package com.ctd.integrador.backend1.service;

import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.PatientDTO;

import java.util.Set;

public interface IPatientService {
    PatientDTO findById(Long id) throws ResourceNotFoundException;
    Set<PatientDTO> findAll();
    void deletePatient(Long id) throws ResourceNotFoundException;
    PatientDTO updatePatient(Long id, PatientDTO patientDTO) throws ResourceNotFoundException;
    PatientDTO addPatient(PatientDTO patientDTO);
    PatientDTO findByDni(String dni) throws ResourceNotFoundException;


}
