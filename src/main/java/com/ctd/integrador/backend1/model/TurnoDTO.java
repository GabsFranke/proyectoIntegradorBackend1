package com.ctd.integrador.backend1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class TurnoDTO {
    private Long id;
    private LocalDate fecha;
    //@JsonIgnoreProperties({"nombre"})
    private OdontologoDTO odontologo;
    @JsonIgnoreProperties({"dni", "fechaIngreso", "domicilios"})
    private PacienteDTO paciente;
}
