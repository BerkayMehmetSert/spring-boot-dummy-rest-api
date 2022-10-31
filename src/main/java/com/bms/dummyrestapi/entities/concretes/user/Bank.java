package com.bms.dummyrestapi.entities.concretes.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "banks")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id")
    private int id;

    @Column(name = "card_expire")
    private String cardExpire;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "currency")
    private String currency;

    @Column(name = "iban")
    private String iban;

    @OneToOne(mappedBy = "bank")
    @JsonIgnore
    private User user;
}
