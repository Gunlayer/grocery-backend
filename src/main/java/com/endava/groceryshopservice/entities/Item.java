package com.endava.groceryshopservice.entities;

import com.endava.groceryshopservice.entities.dto.ItemResponseDTO;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_ITEMS")
public class Item {
    @Id
    @SequenceGenerator(
            name = "grocery_shop_sequence",
            sequenceName = "grocery_shop_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grocery_shop_sequence")
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private User user;

    @Column(name = "size")
    private Integer size;

    public ItemResponseDTO toItemResponseDTO() {
        return ItemResponseDTO.builder()
                .quantity(this.quantity)
                .productId(this.product.getId())
                .image(this.product.getImage())
                .price(this.product.getPrice())
                .name(this.product.getName())
                .sizeType(this.product.getSizeType())
                .size(this.size)
                .build();
    }
}