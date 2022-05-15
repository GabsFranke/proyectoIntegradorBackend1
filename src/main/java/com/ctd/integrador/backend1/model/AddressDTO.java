package com.ctd.integrador.backend1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class AddressDTO {
    private Long id;
    private String street;
    private String number;
    private String city;
    private String state;
    @JsonIgnore
    private PatientDTO patient;

}
