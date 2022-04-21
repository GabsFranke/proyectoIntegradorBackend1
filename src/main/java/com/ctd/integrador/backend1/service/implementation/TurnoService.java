package com.ctd.integrador.backend1.service.implementation;

import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.TurnoDTO;
import com.ctd.integrador.backend1.persistence.entity.Turno;
import com.ctd.integrador.backend1.persistence.repository.ITurnoRepository;
import com.ctd.integrador.backend1.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TurnoService implements ITurnoService {

    private ITurnoRepository repository;
    @Autowired
    ObjectMapper mapper;

    @Autowired
    public void setRepository(ITurnoRepository repository) {
        this.repository = repository;
    }


    @Override
    public TurnoDTO buscarPorId(Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado = repository.findById(id);
        if (turnoBuscado.isPresent()) {
            TurnoDTO turnoDTO = mapper.convertValue(turnoBuscado, TurnoDTO.class);
            return turnoDTO;
        } else {
            throw new ResourceNotFoundException("No se encontro el turno.");
        }
    }

    @Override
    public Set<TurnoDTO> buscarTodos() {
        List<Turno> turnos = repository.findAll();
        Set<TurnoDTO> turnoDTOS = new HashSet<>();

        for (Turno turno : turnos) {
            TurnoDTO turnoDTO = mapper.convertValue(turno, TurnoDTO.class);
            turnoDTOS.add(turnoDTO);
        }
        return turnoDTOS;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if(repository.findById(id).isPresent()) {
            repository.deleteById(id);
        }  else {
            throw new ResourceNotFoundException("No existe el turno solicitado.");
        }
    }

    @Override
    public TurnoDTO actualizarTurno(TurnoDTO turnoDTO) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado = repository.findById(turnoDTO.getId());
        if (turnoBuscado.isPresent()) {
            Turno turno = mapper.convertValue(turnoDTO, Turno.class);
            Turno turnoGuardado = repository.save(turno);
            TurnoDTO turnoDTOGuardado = mapper.convertValue(turnoGuardado, TurnoDTO.class);
            return turnoDTOGuardado;
        } else {
            throw new ResourceNotFoundException("No se encontro el turno.");
        }
    }

    @Override
    public TurnoDTO agregarTurno(TurnoDTO turnoDTO) {
        Turno turno = mapper.convertValue(turnoDTO, Turno.class);
        Turno turnoGuardado = repository.save(turno);
        TurnoDTO turnoDTOGuardado = mapper.convertValue(turnoGuardado, TurnoDTO.class);
        return turnoDTOGuardado;
    }

}
