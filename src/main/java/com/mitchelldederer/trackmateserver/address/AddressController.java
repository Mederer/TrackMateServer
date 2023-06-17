package com.mitchelldederer.trackmateserver.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("addresses")
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO newAddress) {
        return new ResponseEntity<>(addressService.createAddress(newAddress), HttpStatus.CREATED);
    }

    @GetMapping("addresses")
    public ResponseEntity<List<AddressDTO>> getAddresses() {
        return new ResponseEntity<>(addressService.getAddresses(), HttpStatus.OK);
    }

    @GetMapping("addresses/{addressId}")
    public ResponseEntity<AddressDTO> getAddress(@PathVariable int addressId) {
        return new ResponseEntity<>(addressService.getAddress(addressId), HttpStatus.OK);
    }

    @PutMapping("addresses")
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO addressDTO) {
        return new ResponseEntity<>(addressService.updateAddress(addressDTO), HttpStatus.OK);
    }

    @DeleteMapping("address/{addressId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(@PathVariable int addressId) {
        addressService.deleteAddress(addressId);
    }
}
