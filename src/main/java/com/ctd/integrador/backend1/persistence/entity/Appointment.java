package com.ctd.integrador.backend1.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "appointment")
@Getter @Setter
public class Appointment {

    @Id
    @SequenceGenerator(name = "appointment_id_seq", sequenceName = "appointment_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "appointment_id_seq")
    private Long id;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "id_dentist", referencedColumnName = "id")
    private Dentist dentist;

    @ManyToOne
    @JoinColumn(name = "id_patient", referencedColumnName = "id")
    private Patient patient;
}
