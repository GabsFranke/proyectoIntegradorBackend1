package com.ctd.integrador.backend1.persistence.repository;


import com.ctd.integrador.backend1.persistence.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long> {

    @Query
    Patient findByDni(String dni);

}
