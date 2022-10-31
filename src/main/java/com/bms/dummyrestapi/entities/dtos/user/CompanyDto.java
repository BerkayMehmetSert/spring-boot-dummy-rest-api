package com.bms.dummyrestapi.entities.dtos.user;

import lombok.Data;


@Data
public class CompanyDto {

    private String name;

    private String department;

    private AddressDto address;
}
