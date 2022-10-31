package com.bms.dummyrestapi.entities.dtos.user;

import lombok.Data;

@Data
public class UserDto {

    private int id;

    private String firstName;

    private String lastName;

    private String maidenName;

    private int age;

    private String gender;

    private String email;

    private String phone;

    private String username;

    private String password;

    private String birthDate;

    private String image;

    private String bloodGroup;

    private String height;

    private String weight;

    private String eyeColor;

    private String university;

    private HairDto hair;

    private AddressDto address;

    private BankDto bank;

    private CompanyDto company;
}
