package com.ctd.integrador.backend1.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Setter @Getter
public class Address {

    @Id
    @SequenceGenerator(name = "address_id_seq", sequenceName = "address_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "address_id_seq")
    @Column(name = "id")
    private Long id;

    private String street;
    private String number;
    private String city;
    private String state;

    @ManyToOne
    @JoinColumn(name = "id_patient", referencedColumnName = "id")
    @JsonIgnoreProperties("addresses")
    private Patient patient;

}
