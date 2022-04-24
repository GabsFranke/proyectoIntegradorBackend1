package com.ctd.integrador.backend1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class AppointmentDTO {
    private Long id;
    private LocalDate date;
    private DentistDTO dentist;
    @JsonIgnoreProperties({"dni", "registrationDate", "addresses"})
    private PatientDTO patient;
}
