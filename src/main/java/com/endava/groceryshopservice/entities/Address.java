package com.endava.groceryshopservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Table(name = "T_ADDRESSES")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @Column(name = "email")
    private String email;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email")
    private User user;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "apartment")
    private String apartment;
}