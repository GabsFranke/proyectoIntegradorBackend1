package com.ctd.integrador.backend1.service;

import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.TurnoDTO;

import java.util.Set;

public interface ITurnoService {
    TurnoDTO buscarPorId(Integer id) throws ResourceNotFoundException;
    Set<TurnoDTO> buscarTodos();
    void eliminarTurno(Integer id) throws ResourceNotFoundException;
    TurnoDTO actualizarTurno(TurnoDTO turnoDTO) throws ResourceNotFoundException;
    TurnoDTO agregarTurno(TurnoDTO turnoDTO);
}
