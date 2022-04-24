package com.ctd.integrador.backend1.persistence.repository;


import com.ctd.integrador.backend1.persistence.entity.Dentist;
import com.ctd.integrador.backend1.persistence.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface IDentistRepository extends JpaRepository<Dentist,Long> {

    @Query("SELECT o FROM Dentist o WHERE o.lastname = ?1")
    Dentist findByLastName(String lastname);

    @Query("SELECT t FROM Appointment t WHERE t.dentist.lastname = ?1 and t.date = ?2")
    Set<Appointment> findAppointmentByDateAndDentistLastname(String lastname, LocalDate date);



}
