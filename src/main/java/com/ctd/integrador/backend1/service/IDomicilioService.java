package com.ctd.integrador.backend1.service;


import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.DomicilioDTO;

import java.util.Set;

public interface IDomicilioService {
    DomicilioDTO buscarPorId(Long id) throws ResourceNotFoundException;
    Set<DomicilioDTO> buscarTodos();
    void eliminarDomicilio(Long id) throws ResourceNotFoundException;
    DomicilioDTO actualizarDomicilio(Long id, DomicilioDTO domicilioDTO) throws ResourceNotFoundException;
    DomicilioDTO agregarDomicilio(DomicilioDTO domicilioDTO);
}
