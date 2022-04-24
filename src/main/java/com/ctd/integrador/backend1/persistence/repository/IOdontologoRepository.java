package com.ctd.integrador.backend1.persistence.repository;

import com.ctd.integrador.backend1.persistence.entity.Odontologo;
import com.ctd.integrador.backend1.persistence.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo,Long> {

    @Query("SELECT o FROM Odontologo o WHERE o.apellido = ?1")
    Odontologo findByLastName(String apellido);

    @Query("SELECT t FROM Turno t WHERE t.odontologo.apellido = ?1 and t.fecha = ?2")
    Set<Turno> findAppointmentByDateAndDrLastname(String apellido, LocalDate fecha);



}
