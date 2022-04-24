package com.ctd.integrador.backend1.service;

import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.TurnoDTO;
import com.ctd.integrador.backend1.persistence.entity.Turno;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;
import java.util.Set;

public interface ITurnoService {
    TurnoDTO buscarPorId(Long id) throws ResourceNotFoundException;
    Set<TurnoDTO> buscarTodos();
    void eliminarTurno(Long id) throws ResourceNotFoundException;
    TurnoDTO actualizarTurno(TurnoDTO turnoDTO) throws ResourceNotFoundException;
    TurnoDTO agregarTurno(TurnoDTO turnoDTO);
    Set<TurnoDTO> findTurnoByPacienteId(Long id);
    Set<TurnoDTO> findTurnoByOdontologoId(Long id);

}
