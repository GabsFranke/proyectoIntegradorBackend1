package com.ctd.integrador.backend1.service.implementation;

import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.DomicilioDTO;
import com.ctd.integrador.backend1.persistence.entity.Domicilio;
import com.ctd.integrador.backend1.persistence.repository.IDomicilioRepository;
import com.ctd.integrador.backend1.service.IDomicilioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DomicilioService implements IDomicilioService {

    private IDomicilioRepository repository;
    @Autowired
    ObjectMapper mapper;

    @Autowired
    public void setRepository(IDomicilioRepository repository) {
        this.repository = repository;
    }


    @Override
    public DomicilioDTO buscarPorId(Long id) throws ResourceNotFoundException {
        Optional<Domicilio> domicilioBuscado = Optional.of(repository.findById(id).get());
        if(domicilioBuscado.isPresent()) {
            DomicilioDTO domicilioDTO = mapper.convertValue(domicilioBuscado, DomicilioDTO.class);
            return domicilioDTO;
        } else {
            throw new ResourceNotFoundException("No se encontro el odontologo con el id solicitado.");
        }
    }

    @Override
    public Set<DomicilioDTO> buscarTodos() {
        List<Domicilio> domicilios = repository.findAll();
        Set<DomicilioDTO> domicilioDTO = new HashSet<>();

        for (Domicilio domicilio : domicilios) {
            domicilioDTO.add(mapper.convertValue(domicilio, DomicilioDTO.class));
        }

        return domicilioDTO;
    }

    @Override
    public void eliminarDomicilio(Long id) throws ResourceNotFoundException {
        Optional<Domicilio> domicilioBuscado = Optional.of(repository.findById(id).get());
        if(domicilioBuscado.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No se encontro el odontologo con el id solicitado.");
        }
    }

    @Override
    public DomicilioDTO actualizarDomicilio(Long id, DomicilioDTO domicilioDTO) throws ResourceNotFoundException {
        Optional<Domicilio> domicilioBuscado = Optional.of(repository.findById(id).get());
        if(domicilioBuscado.isPresent()) {
            Domicilio domicilio = mapper.convertValue(domicilioDTO, Domicilio.class);
            domicilio.setId(Long.valueOf(id));
            domicilio = repository.save(domicilio);
            return mapper.convertValue(domicilio, DomicilioDTO.class);
        } else {
            throw new ResourceNotFoundException("No se encontro el odontologo con el id solicitado.");
        }
    }

    @Override
    public DomicilioDTO agregarDomicilio(DomicilioDTO domicilioDTO) {
        Domicilio domicilio = mapper.convertValue(domicilioDTO, Domicilio.class);
        Domicilio domicilioGuardado = repository.save(domicilio);
        DomicilioDTO domicilioDTOGuardado = mapper.convertValue(domicilioGuardado, DomicilioDTO.class);
        return domicilioDTOGuardado;
    }
}
