package com.bms.dummyrestapi.entities.dtos.user;

import lombok.Data;


@Data
public class AddressDto {

    private String address;

    private String city;

    private String postalCode;

    private String state;

}
