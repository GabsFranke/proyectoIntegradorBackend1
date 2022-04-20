package com.ctd.integrador.backend1.service.implementation;

import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.OdontologoDTO;
import com.ctd.integrador.backend1.persistence.entity.Odontologo;
import com.ctd.integrador.backend1.persistence.repository.IOdontologoRepository;
import com.ctd.integrador.backend1.service.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OdontologoService implements IOdontologoService {

    private IOdontologoRepository repository;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    public void setRepository(IOdontologoRepository repository) {
        this.repository = repository;
    }

    @Override
    public OdontologoDTO buscarPorId(Long id) throws ResourceNotFoundException {

        Optional<Odontologo> odontologoBuscado = Optional.of(repository.findById((id)).get());
        if(odontologoBuscado.isPresent()) {
            OdontologoDTO odontologoDTO = mapper.convertValue(odontologoBuscado, OdontologoDTO.class);
            return odontologoDTO;
        } else {
            throw new ResourceNotFoundException("No se encontro el odontologo solicitado.");
        }

    }

    @Override
    public Set<OdontologoDTO> buscarTodos() {
        List<Odontologo> odontologosList = repository.findAll();
        Set<OdontologoDTO> odontologoDTOS = new HashSet<>();

        for(Odontologo odontologo : odontologosList) {
            OdontologoDTO odontologoDTO = mapper.convertValue(odontologo, OdontologoDTO.class);
            odontologoDTOS.add(odontologoDTO);
        }

        return odontologoDTOS;
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = Optional.of(repository.findById(id).get());
        if(odontologoBuscado.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No se encontro el odontologo solicitado.");
        }
    }

    @Override
    public OdontologoDTO actualizarOdontologo(Long id, OdontologoDTO odontologoDTO) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = Optional.of(repository.findById(id).get());
        if(odontologoBuscado.isPresent()) {
            Odontologo odontologo = mapper.convertValue(odontologoDTO, Odontologo.class);
            odontologo.setId(Long.valueOf(id));
            odontologo = repository.save(odontologo);
            return mapper.convertValue(odontologo, OdontologoDTO.class);
        } else {
            throw new ResourceNotFoundException("No se encontro el odontologo solicitado.");
        }
    }

    @Override
    public OdontologoDTO agregarOdontologo(OdontologoDTO odontologoDTO) {
        Odontologo odontologo = mapper.convertValue(odontologoDTO, Odontologo.class);
        Odontologo odontologoGuardado = repository.save(odontologo);
        OdontologoDTO odontologoDTOGuardado = mapper.convertValue(odontologoGuardado, OdontologoDTO.class);
        return odontologoDTOGuardado;
    }

    @Override
    public OdontologoDTO findByLastName(String apellido) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = Optional.of(repository.findByLastName(apellido));
        if(odontologoBuscado.isPresent()) {
            OdontologoDTO odontologoDTO = mapper.convertValue(odontologoBuscado, OdontologoDTO.class);
            return odontologoDTO;
        } else {
            throw new ResourceNotFoundException("No se encontro el odontologo solicitado.");
        }
    }
}
