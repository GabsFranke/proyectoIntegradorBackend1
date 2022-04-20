package com.ctd.integrador.backend1.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "odontologo")
@Setter @Getter
public class Odontologo {
    @Id
    @SequenceGenerator(name = "odontologo_id_seq", sequenceName = "odontologo_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "odontologo_id_seq")
    private Long id;
    private String nombre;
    private String apellido;
    private Integer matricula;

//    @OneToMany(mappedBy = "odontologo", cascade = CascadeType.ALL)
//    private Set<Turno> turnos;
}
