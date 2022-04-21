package com.ctd.integrador.backend1.service.implementation;

import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.PacienteDTO;
import com.ctd.integrador.backend1.persistence.entity.Domicilio;
import com.ctd.integrador.backend1.persistence.entity.Paciente;
import com.ctd.integrador.backend1.persistence.repository.IPacienteRepository;
import com.ctd.integrador.backend1.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PacienteService implements IPacienteService {

    private IPacienteRepository repository;
    @Autowired
    ObjectMapper mapper;

    @Autowired
    public void setRepository(IPacienteRepository repository) {
        this.repository = repository;
    }


    @Override
    public PacienteDTO buscarPorId(Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = repository.findById(id);
        if(pacienteBuscado.isPresent()) {
            PacienteDTO pacienteDTO = mapper.convertValue(pacienteBuscado, PacienteDTO.class);
            return pacienteDTO;
        } else {
            throw new ResourceNotFoundException("No existe el paciente solicitado.");
        }
    }

    @Override
    public Set<PacienteDTO> buscarTodos() {
        List<Paciente> pacienteList = repository.findAll();
        Set<PacienteDTO> pacienteDTOS = new HashSet<>();

        for(Paciente paciente : pacienteList) {
            PacienteDTO pacienteDTO = mapper.convertValue(paciente, PacienteDTO.class);
            pacienteDTOS.add(pacienteDTO);
        }

        return pacienteDTOS;
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        if(repository.findById(id).isPresent()) {
            repository.deleteById(id);
        }  else {
            throw new ResourceNotFoundException("No existe el paciente solicitado.");
        }
    }

    @Override
    public PacienteDTO actualizarPaciente(Long id, PacienteDTO pacienteDTO) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = repository.findById(id);
        if(pacienteBuscado.isPresent()) {
            Paciente paciente = mapper.convertValue(pacienteDTO, Paciente.class);
            paciente.setId(Long.valueOf(id));
            paciente = repository.save(paciente);
            return mapper.convertValue(paciente, PacienteDTO.class);
        } else {
            throw new ResourceNotFoundException("No existe el paciente solicitado.");
        }
    }

    @Override
    public PacienteDTO agregarPaciente(PacienteDTO pacienteDTO) {
        Paciente paciente = mapper.convertValue(pacienteDTO, Paciente.class);
        for(Domicilio domicilio : paciente.getDomicilios()) {
            domicilio.setPaciente(paciente);
        }
        Paciente pacienteGuardado = repository.save(paciente);
        PacienteDTO pacienteDTOGuardado = mapper.convertValue(pacienteGuardado, PacienteDTO.class);
        return pacienteDTOGuardado;
    }

    @Override
    public PacienteDTO findPacienteByDni(String dni) throws ResourceNotFoundException {
        Paciente pacienteBuscado = repository.findPacienteByDni(dni);
        if(pacienteBuscado != null) {
            PacienteDTO pacienteDTO = mapper.convertValue(pacienteBuscado, PacienteDTO.class);
            return pacienteDTO;
        } else {
            throw new ResourceNotFoundException("No existe el paciente solicitado.");
        }
    }
}