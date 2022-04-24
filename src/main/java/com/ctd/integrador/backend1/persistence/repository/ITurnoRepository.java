package com.ctd.integrador.backend1.persistence.repository;

import com.ctd.integrador.backend1.persistence.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {

    @Query
    Set<Turno> findAllByPacienteId(Long id);

    @Query
    Set<Turno> findAllByOdontologoId(Long id);

    @Query
    Set<Turno> findAllByPaciente_dniAndFecha (String dni, LocalDate fecha);


}
