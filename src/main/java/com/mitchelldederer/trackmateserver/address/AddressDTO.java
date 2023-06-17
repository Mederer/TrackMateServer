package com.mitchelldederer.trackmateserver.address;

public record AddressDTO(
        int addressId,
        String streetNumber,
        String streetName,
        String suburb,
        State state,
        String postcode
) {
}
