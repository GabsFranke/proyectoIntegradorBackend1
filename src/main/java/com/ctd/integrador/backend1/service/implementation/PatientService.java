package com.ctd.integrador.backend1.service.implementation;


import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.PatientDTO;
import com.ctd.integrador.backend1.persistence.entity.Address;
import com.ctd.integrador.backend1.persistence.entity.Patient;
import com.ctd.integrador.backend1.persistence.repository.IPatientRepository;
import com.ctd.integrador.backend1.service.IPatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PatientService implements IPatientService {

    private IPatientRepository patientRepository;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    public void setPatientRepository(IPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public PatientDTO findById(Long id) throws ResourceNotFoundException {
        Optional<Patient> patientFound = patientRepository.findById(id);
        if(patientFound.isPresent()) {
            PatientDTO patientDTO = mapper.convertValue(patientFound, PatientDTO.class);
            return patientDTO;
        } else {
            throw new ResourceNotFoundException("The patient does not exist.");
        }
    }

    @Override
    public Set<PatientDTO> findAll() {
        List<Patient> patientList = patientRepository.findAll();
        Set<PatientDTO> patientDTOS = new HashSet<>();

        for(Patient patient : patientList) {
            PatientDTO patientDTO = mapper.convertValue(patient, PatientDTO.class);
            patientDTOS.add(patientDTO);
        }

        return patientDTOS;
    }

    @Override
    public void deletePatient(Long id) throws ResourceNotFoundException {
        if(patientRepository.findById(id).isPresent()) {
            patientRepository.deleteById(id);
        }  else {
            throw new ResourceNotFoundException("The patient does not exist.");
        }
    }

    @Override
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) throws ResourceNotFoundException {
        Optional<Patient> patientFound = patientRepository.findById(id);
        if(patientFound.isPresent()) {
            Patient patient = mapper.convertValue(patientDTO, Patient.class);
            patient.setId(Long.valueOf(id));
            patient = patientRepository.save(patient);
            return mapper.convertValue(patient, PatientDTO.class);
        } else {
            throw new ResourceNotFoundException("The patient does not exist.");
        }
    }

    @Override
    public PatientDTO addPatient(PatientDTO patientDTO) {
        Patient patient = mapper.convertValue(patientDTO, Patient.class);
        for(Address address : patient.getAddresses()) {
            address.setPatient(patient);
        }
        Patient patientSaved = patientRepository.save(patient);
        PatientDTO patientDTOSaved = mapper.convertValue(patientSaved, PatientDTO.class);
        return patientDTOSaved;
    }

    @Override
    public PatientDTO findByDni(String dni) throws ResourceNotFoundException {
        Patient patientFound = patientRepository.findByDni(dni);
        if(patientFound != null) {
            PatientDTO patientDTO = mapper.convertValue(patientFound, PatientDTO.class);
            return patientDTO;
        } else {
            throw new ResourceNotFoundException("The patient does not exist.");
        }
    }


}