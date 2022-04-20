package com.ctd.integrador.backend1.service;

import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.OdontologoDTO;

import java.util.Set;

public interface IOdontologoService {
    OdontologoDTO buscarPorId(Integer id) throws ResourceNotFoundException;
    Set<OdontologoDTO> buscarTodos();
    void eliminarOdontologo(Integer id) throws ResourceNotFoundException;
    OdontologoDTO actualizarOdontologo(Integer id, OdontologoDTO odontologoDTO) throws ResourceNotFoundException;
    OdontologoDTO agregarOdontologo(OdontologoDTO odontologoDTO);
    OdontologoDTO findByLastName(String apellido) throws ResourceNotFoundException;
}