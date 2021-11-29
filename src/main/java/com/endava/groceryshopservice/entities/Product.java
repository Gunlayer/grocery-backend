package com.endava.groceryshopservice.entities;

import com.endava.groceryshopservice.entities.types.SizeTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.List;

@Data
@Entity
@Builder
@Table(name = "T_PRODUCTS")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @SequenceGenerator(
            name = "grocery_shop_sequence",
            sequenceName = "grocery_shop_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grocery_shop_sequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "image_url")
    private String image;

    @Column(name = "name")
    private String name;

    @Column(name = "price", scale = 2)
    private Double price;

    @Column(name = "rating", scale = 1)
    private Double rating;

    @Column(name = "description")
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "sizes")
    private List<Integer> sizes;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "size_type")
    private SizeTypes sizeType;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private Views views;

    @OneToOne(mappedBy = "product")
    private OrderContent orderContent;

    @OneToMany(mappedBy = "product")
    private List<Review> productsReviews;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image=" + image +
                ", price=" + price +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", sizes=" + sizes +
                ", sizeType=" + sizeType +
                '}';
    }
}