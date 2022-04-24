package com.ctd.integrador.backend1.controller;


import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.AddressDTO;
import com.ctd.integrador.backend1.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/addresses")
@CrossOrigin(origins = "*")
public class AddressController {

    @Autowired
    IAddressService addressService;

    @GetMapping
    public ResponseEntity<Set<AddressDTO>> findAll() {
        return ResponseEntity.ok(addressService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> findById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(addressService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AddressDTO> addAddress(@RequestBody AddressDTO addressDTO) {
        return ResponseEntity.ok(addressService.addAddress(addressDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) throws ResourceNotFoundException {
        addressService.updateAddress(id, addressDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id) throws ResourceNotFoundException {
        addressService.deleteAddress(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
