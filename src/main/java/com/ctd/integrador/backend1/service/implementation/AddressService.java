package com.ctd.integrador.backend1.service.implementation;


import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.AddressDTO;
import com.ctd.integrador.backend1.persistence.entity.Address;
import com.ctd.integrador.backend1.persistence.repository.IAddressRepository;
import com.ctd.integrador.backend1.service.IAddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AddressService implements IAddressService {

    private IAddressRepository repository;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    public void setAddressRepository(IAddressRepository repository) {
        this.repository = repository;
    }


    @Override
    public AddressDTO findById(Long id) throws ResourceNotFoundException {
        Optional<Address> addressFound = repository.findById(id);
        if(addressFound.isPresent()) {
            AddressDTO addressDTO = mapper.convertValue(addressFound, AddressDTO.class);
            return addressDTO;
        } else {
            throw new ResourceNotFoundException("Address not found.");
        }
    }

    @Override
    public Set<AddressDTO> findAll() {
        List<Address> addresses = repository.findAll();
        Set<AddressDTO> addressDTO = new HashSet<>();

        for (Address address : addresses) {
            addressDTO.add(mapper.convertValue(address, AddressDTO.class));
        }

        return addressDTO;
    }

    @Override
    public void deleteAddress(Long id) throws ResourceNotFoundException {
        if(repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Address not found.");
        }
    }

    @Override
    public AddressDTO updateAddress(Long id, AddressDTO addressDTO) throws ResourceNotFoundException {
        Optional<Address> addressFound = repository.findById(id);
        if(addressFound.isPresent()) {
            Address address = mapper.convertValue(addressDTO, Address.class);
            address.setId(Long.valueOf(id));
            address = repository.save(address);
            return mapper.convertValue(address, AddressDTO.class);
        } else {
            throw new ResourceNotFoundException("Address not found.");
        }
    }

    @Override
    public AddressDTO addAddress(AddressDTO addressDTO) {
        Address address = mapper.convertValue(addressDTO, Address.class);
        Address savedAddress = repository.save(address);
        AddressDTO addressDTOFound = mapper.convertValue(savedAddress, AddressDTO.class);
        return addressDTOFound;
    }
}
