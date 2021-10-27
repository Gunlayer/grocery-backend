package com.endava.groceryshopservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "T_VIEWS")
@NoArgsConstructor
public class Views {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @MapsId
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id")
    private Product product;

    @Column(name = "number")
    private Long number;

    public Views(Product product, long number) {
        this.product = product;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Views{" +
                "id=" + id +
                ", number=" + number +
                '}';
    }
}