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
@Table(name = "hairs")
public class Hair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hair_id")
    private int id;

    @Column(name = "hair_color")
    private String color;

    @Column(name = "type")
    private String type;

    @OneToOne(mappedBy = "hair")
    @JsonIgnore
    private User user;
}
