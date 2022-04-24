package com.ctd.integrador.backend1.service;

import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.DentistDTO;
import com.ctd.integrador.backend1.model.AppointmentDTO;

import java.util.Set;

public interface IDentistService {
    DentistDTO findById(Long id) throws ResourceNotFoundException;
    Set<DentistDTO> findAll();
    void deleteDentist(Long id) throws ResourceNotFoundException;
    DentistDTO updateDentist(Long id, DentistDTO dentistDTO) throws ResourceNotFoundException;
    DentistDTO addDentist(DentistDTO dentistDTO);
    DentistDTO findByLastName(String lastname) throws ResourceNotFoundException;
    Set<AppointmentDTO> findAppointmentByDateAndDentistLastname(String lastname, String date);

}