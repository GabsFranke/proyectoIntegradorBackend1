package com.ctd.integrador.backend1.service;

import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.PacienteDTO;

import java.util.Set;

public interface IPacienteService {
    PacienteDTO buscarPorId(Integer id) throws ResourceNotFoundException;
    Set<PacienteDTO> buscarTodos();
    void eliminarPaciente(Integer id) throws ResourceNotFoundException;
    PacienteDTO actualizarPaciente(Integer id, PacienteDTO pacienteDTO) throws ResourceNotFoundException;
    PacienteDTO agregarPaciente(PacienteDTO pacienteDTO);
    PacienteDTO findPacienteByDni(String dni) throws ResourceNotFoundException;
}
