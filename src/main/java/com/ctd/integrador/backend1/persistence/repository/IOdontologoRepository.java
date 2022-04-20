package com.ctd.integrador.backend1.persistence.repository;

import com.ctd.integrador.backend1.persistence.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo,Long> {

    @Query("select o from Odontologo o where o.apellido = ?1")
    Odontologo findByLastName(String apellido);

}
