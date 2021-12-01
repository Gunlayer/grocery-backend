package com.endava.groceryshopservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "T_ORDERS")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @SequenceGenerator(
            name = "grocery_shop_sequence",
            sequenceName = "grocery_shop_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grocery_shop_sequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "price")
    private Double price;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderContent> orderContent;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", orderDate=" + orderDate +
                ", orderContent=" + orderContent +
                '}';
    }
}