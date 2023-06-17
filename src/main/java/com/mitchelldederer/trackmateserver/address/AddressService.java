package com.mitchelldederer.trackmateserver.address;

import com.mitchelldederer.trackmateserver.exceptions.AppEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressDTO createAddress(AddressDTO addressDTO) {
        Address address = new Address();

        address.setStreetNumber(addressDTO.streetNumber());
        address.setStreetName(addressDTO.streetName());
        address.setSuburb(addressDTO.suburb());
        address.setPostcode(addressDTO.postcode());
        address.setState(addressDTO.state());

        addressRepository.save(address);

        return AddressMapper.modelToDto(address);
    }

    public List<AddressDTO> getAddresses() {
        Iterable<Address> addresses = addressRepository.findAll();

        List<AddressDTO> addressDTOList = new ArrayList<>();

        addresses.forEach(address -> addressDTOList.add(AddressMapper.modelToDto(address)));

        return addressDTOList;
    }

    public AddressDTO getAddress(int addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(AppEntityNotFoundException::new);

        return AddressMapper.modelToDto(address);
    }

    public AddressDTO updateAddress(AddressDTO addressDTO) {
        Address address = addressRepository.findById(addressDTO.addressId()).orElseThrow(AppEntityNotFoundException::new);

        address.setStreetNumber(addressDTO.streetNumber());
        address.setStreetName(addressDTO.streetName());
        address.setSuburb(addressDTO.suburb());
        address.setPostcode(addressDTO.postcode());
        address.setState(addressDTO.state());

        addressRepository.save(address);
        return AddressMapper.modelToDto(address);
    }

    public void deleteAddress(int addressId) {
        addressRepository.deleteById(addressId);
    }
}
