package com.ctd.integrador.backend1.service;


import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.AddressDTO;

import java.util.Set;

public interface IAddressService {
    AddressDTO findById(Long id) throws ResourceNotFoundException;
    Set<AddressDTO> findAll();
    void deleteAddress(Long id) throws ResourceNotFoundException;
    AddressDTO updateAddress(Long id, AddressDTO addressDTO) throws ResourceNotFoundException;
    AddressDTO addAddress(AddressDTO addressDTO);

}
