package com.bms.dummyrestapi.entities.concretes.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @NotBlank(message = "First name cannot be empty")
    @NotEmpty(message = "First name cannot be empty")
    @NotNull(message = "First name cannot be empty")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @NotEmpty(message = "Last name cannot be empty")
    @NotNull(message = "Last name cannot be empty")
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "maiden_name")
    private String maidenName;

    @Min(value = 18, message = "Age must be greater than 18")
    @Column(name = "age")
    private int age;

    @NotBlank(message = "Gender cannot be empty")
    @NotEmpty(message = "Gender cannot be empty")
    @NotNull(message = "Gender cannot be empty")
    @Column(name = "gender")
    private String gender;

    @NotBlank(message = "Email cannot be empty")
    @NotEmpty(message = "Email cannot be empty")
    @NotNull(message = "Email cannot be empty")
    @Email(message = "Email format is not correct", regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Phone cannot be empty")
    @NotEmpty(message = "Phone cannot be empty")
    @NotNull(message = "Phone cannot be empty")
    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", message = "Phone format is not correct")
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "Username cannot be empty")
    @NotEmpty(message = "Username cannot be empty")
    @NotNull(message = "Username cannot be empty")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @NotEmpty(message = "Password cannot be empty")
    @NotNull(message = "Password cannot be empty")
    @Column(name = "password")
    private String password;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "image")
    private String image;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "height")
    private String height;

    @Column(name = "weight")
    private String weight;

    @Column(name = "eye_color")
    private String eyeColor;

    @Column(name = "university")
    private String university;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hair_id")
    private Hair hair;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

}
