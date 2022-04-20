package com.ctd.integrador.backend1.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "turno")
@Getter @Setter
public class Turno {

    @Id
    @SequenceGenerator(name = "turno_id_seq", sequenceName = "turno_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "turno_id_seq")
    private Long id;
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "id_odontologo", referencedColumnName = "id")
    private Odontologo odontologo;

    @ManyToOne
    @JoinColumn(name = "id_paciente", referencedColumnName = "id")
    private Paciente paciente;
}
