package com.ctd.integrador.backend1.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dentist")
@Setter @Getter
public class Dentist {
    @Id
    @SequenceGenerator(name = "dentist_id_seq", sequenceName = "dentist_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "dentist_id_seq")
    private Long id;
    private String name;
    private String lastname;
    private String registrationNumber;



}
