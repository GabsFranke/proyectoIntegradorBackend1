package com.ctd.integrador.backend1.service.implementation;


import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.AppointmentDTO;
import com.ctd.integrador.backend1.model.DentistDTO;
import com.ctd.integrador.backend1.persistence.entity.Appointment;
import com.ctd.integrador.backend1.persistence.entity.Dentist;
import com.ctd.integrador.backend1.persistence.repository.IDentistRepository;
import com.ctd.integrador.backend1.service.IDentistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DentistService implements IDentistService {

    private IDentistRepository repository;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    public void setDentistRepository(IDentistRepository repository) {
        this.repository = repository;
    }

    @Override
    public DentistDTO findById(Long id) throws ResourceNotFoundException {
        Optional<Dentist> dentistFound = repository.findById(id);
        if(dentistFound.isPresent()) {
            DentistDTO dentistDTO = mapper.convertValue(dentistFound, DentistDTO.class);
            return dentistDTO;
        } else {
            throw new ResourceNotFoundException("The requested dentist doesn't exist.");
        }
    }

    @Override
    public Set<DentistDTO> findAll() {
        List<Dentist> dentistsList = repository.findAll();
        Set<DentistDTO> dentistDTOS = new HashSet<>();

        for(Dentist dentist : dentistsList) {
            DentistDTO dentistDTO = mapper.convertValue(dentist, DentistDTO.class);
            dentistDTOS.add(dentistDTO);
        }

        return dentistDTOS;
    }

    @Override
    public void deleteDentist(Long id) throws ResourceNotFoundException {
        if(repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("The requested dentist doesn't exist.");
        }
    }

    @Override
    public DentistDTO updateDentist(Long id, DentistDTO dentistDTO) throws ResourceNotFoundException {
        Optional<Dentist> dentistFound = repository.findById(id);
        if(dentistFound.isPresent()) {
            Dentist dentist = mapper.convertValue(dentistDTO, Dentist.class);
            dentist.setId(Long.valueOf(id));
            dentist = repository.save(dentist);
            return mapper.convertValue(dentist, DentistDTO.class);
        } else {
            throw new ResourceNotFoundException("The requested dentist doesn't exist.");
        }
    }

    @Override
    public DentistDTO addDentist(DentistDTO dentistDTO) {
        Dentist dentist = mapper.convertValue(dentistDTO, Dentist.class);
        Dentist dentistFound = repository.save(dentist);
        DentistDTO dentistDTOSaved = mapper.convertValue(dentistFound, DentistDTO.class);
        return dentistDTOSaved;
    }

    @Override
    public DentistDTO findByLastName(String lastname) throws ResourceNotFoundException {
        Dentist dentistFound = repository.findByLastName(lastname);
        if(dentistFound != null) {
            DentistDTO dentistDTO = mapper.convertValue(dentistFound, DentistDTO.class);
            return dentistDTO;
        } else {
            throw new ResourceNotFoundException("The requested dentist doesn't exist.");
        }
    }

    @Override
    public Set<AppointmentDTO> findAppointmentByDateAndDentistLastname(String lastname, String date) {
        Set<Appointment> appointmentsFound = repository.findAppointmentByDateAndDentistLastname(lastname, LocalDate.parse(date));
        Set<AppointmentDTO> appointmentsFoundDTP = new HashSet<>();
        for(Appointment appointment : appointmentsFound) {
            AppointmentDTO appointmentDTO = mapper.convertValue(appointment, AppointmentDTO.class);
            appointmentsFoundDTP.add(appointmentDTO);
        }
        return appointmentsFoundDTP;
    }
}
