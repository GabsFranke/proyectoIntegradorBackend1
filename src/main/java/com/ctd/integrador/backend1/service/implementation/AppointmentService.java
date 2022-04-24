package com.ctd.integrador.backend1.service.implementation;


import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.AppointmentDTO;
import com.ctd.integrador.backend1.persistence.entity.Appointment;
import com.ctd.integrador.backend1.persistence.repository.IAppointmentRepository;
import com.ctd.integrador.backend1.service.IAppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppointmentService implements IAppointmentService {

    private IAppointmentRepository repository;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    public void setAppointmentRepository(IAppointmentRepository repository) {
        this.repository = repository;
    }


    @Override
    public AppointmentDTO findById(Long id) throws ResourceNotFoundException {
        Optional<Appointment> appointmentFound = repository.findById(id);
        if (appointmentFound.isPresent()) {
            AppointmentDTO appointmentDTO = mapper.convertValue(appointmentFound, AppointmentDTO.class);
            return appointmentDTO;
        } else {
            throw new ResourceNotFoundException("The appointment doesn't exist.");
        }
    }

    @Override
    public Set<AppointmentDTO> findAll() {
        List<Appointment> appointments = repository.findAll();
        Set<AppointmentDTO> appointmentDTOS = new HashSet<>();

        for (Appointment appointment : appointments) {
            AppointmentDTO appointmentDTO = mapper.convertValue(appointment, AppointmentDTO.class);
            appointmentDTOS.add(appointmentDTO);
        }
        return appointmentDTOS;
    }

    @Override
    public void deleteAppointment(Long id) throws ResourceNotFoundException {
        if(repository.findById(id).isPresent()) {
            repository.deleteById(id);
        }  else {
            throw new ResourceNotFoundException("The appointment doesn't exist.");
        }
    }

    @Override
    public AppointmentDTO updateAppointment(AppointmentDTO appointmentDTO) throws ResourceNotFoundException {
        Optional<Appointment> appointmentFound = repository.findById(appointmentDTO.getId());
        if (appointmentFound.isPresent()) {
            Appointment appointment = mapper.convertValue(appointmentDTO, Appointment.class);
            Appointment appointmentSaved = repository.save(appointment);
            AppointmentDTO appointmentDTOSaved = mapper.convertValue(appointmentSaved, AppointmentDTO.class);
            return appointmentDTOSaved;
        } else {
            throw new ResourceNotFoundException("The appointment doesn't exist.");
        }
    }

    @Override
    public AppointmentDTO addAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = mapper.convertValue(appointmentDTO, Appointment.class);
        Appointment appointmentSaved = repository.save(appointment);
        AppointmentDTO appointmentDTOSaved = mapper.convertValue(appointmentSaved, AppointmentDTO.class);
        return appointmentDTOSaved;
    }

    @Override
    public Set<AppointmentDTO> findAppointmentByPatientId(Long id) {
        Set<Appointment> appointmentsFound = repository.findAllByPatientId(id);
        Set<AppointmentDTO> appointmentsFoundDTO = new HashSet<>();
        for(Appointment appointment : appointmentsFound) {
            AppointmentDTO appointmentDTO = mapper.convertValue(appointment, AppointmentDTO.class);
            appointmentsFoundDTO.add(appointmentDTO);
        }
        return appointmentsFoundDTO;
    }

    @Override
    public Set<AppointmentDTO> findAppointmentByDentistId(Long id) {
        Set<Appointment> appointmentsFound = repository.findAllByDentistId(id);
        Set<AppointmentDTO> appointmentsFoundDTO = new HashSet<>();
        for(Appointment appointment : appointmentsFound) {
            AppointmentDTO appointmentDTO = mapper.convertValue(appointment, AppointmentDTO.class);
            appointmentsFoundDTO.add(appointmentDTO);
        }
        return appointmentsFoundDTO;
    }

}
