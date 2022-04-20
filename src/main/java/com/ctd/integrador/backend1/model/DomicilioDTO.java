package com.ctd.integrador.backend1.model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class DomicilioDTO {
    private Integer id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;
    @JsonIncludeProperties({"id"})
    private PacienteDTO paciente;

}
