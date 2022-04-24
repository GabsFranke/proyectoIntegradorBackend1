package com.ctd.integrador.backend1.service;

import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.AppointmentDTO;

import java.util.Set;

public interface IAppointmentService {
    AppointmentDTO findById(Long id) throws ResourceNotFoundException;
    Set<AppointmentDTO> findAll();
    void deleteAppointment(Long id) throws ResourceNotFoundException;
    AppointmentDTO updateAppointment(AppointmentDTO appointmentDTO) throws ResourceNotFoundException;
    AppointmentDTO addAppointment(AppointmentDTO appointmentDTO);
    Set<AppointmentDTO> findAppointmentByPatientId(Long id);
    Set<AppointmentDTO> findAppointmentByDentistId(Long id);

}
