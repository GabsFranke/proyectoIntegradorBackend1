package com.ctd.integrador.backend1.persistence.repository;

import com.ctd.integrador.backend1.persistence.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query
    Set<Appointment> findAllByPatientId(Long id);

    @Query
    Set<Appointment> findAllByDentistId(Long id);


}
