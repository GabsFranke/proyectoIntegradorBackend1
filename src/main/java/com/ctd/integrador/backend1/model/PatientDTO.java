package com.ctd.integrador.backend1.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Setter @Getter
public class PatientDTO {
    private Long id;
    private String name;
    private String lastname;
    private String dni;
    private LocalDate registrationDate;
    private Set<AddressDTO> addresses;

}




