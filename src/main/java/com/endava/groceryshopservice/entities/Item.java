package com.endava.groceryshopservice.entities;

import com.endava.groceryshopservice.entities.dto.ItemResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_ITEMS")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="cart_id", nullable=false, updatable = false)
    private Cart cart;

    public ItemResponseDTO toItemResponseDTO(){
        ItemResponseDTO itemResponseDTO = ItemResponseDTO.builder()
                .id(this.id)
                .quantity(this.quantity)
                .cart_id(this.cart.getId())
                .product_id(this.product.getId())
                .image(this.product.getImage())
                .price(this.product.getPrice())
                .name(this.product.getName())
                .rating(this.product.getRating())
                .description(this.product.getDescription())
                .sizeType(this.product.getSizeType())
                .build();
        return itemResponseDTO;
    }
}