package com.ctd.integrador.backend1.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "patient")
@Setter @Getter
public class Patient {

    @Id
    @SequenceGenerator(name = "patient_seq", sequenceName = "patient_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "patient_seq")
    private Long id;
    private String name;
    private String lastname;
    private String dni;
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private Set<Address> addresses;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private Set<Appointment> appointments;

}
