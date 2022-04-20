package com.ctd.integrador.backend1.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "domicilio")
@Setter @Getter
public class Domicilio {

    @Id
    @SequenceGenerator(name = "domicilio_id_seq", sequenceName = "domicilio_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "domicilio_id_seq")
    @Column(name = "id")
    private Long id;

    private String calle;
    private String numero;
    private String localidad;
    private String provincia;

    //optional = false,
    @ManyToOne
    @JoinColumn(name = "id_paciente", referencedColumnName = "id")
    @JsonIgnoreProperties("domicilios")
    private Paciente paciente;

}
