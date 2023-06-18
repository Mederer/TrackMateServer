package com.mitchelldederer.trackmateserver.address;

public class AddressMapper {
    public static AddressDTO modelToDto(Address address) {
        return new AddressDTO(
                address.getAddressId(),
                address.getStreetNumber(),
                address.getStreetName(),
                address.getSuburb(),
                address.getState(),
                address.getPostcode()
        );
    }

    public static Address dtoToModel(AddressDTO addressDTO) {
        return new Address(
                addressDTO.addressId(),
                addressDTO.streetNumber(),
                addressDTO.streetName(),
                addressDTO.suburb(),
                addressDTO.postcode(),
                addressDTO.state()
        );
    }
}
