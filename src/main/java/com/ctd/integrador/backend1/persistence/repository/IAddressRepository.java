package com.ctd.integrador.backend1.persistence.repository;

import com.ctd.integrador.backend1.persistence.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Long> {
}
