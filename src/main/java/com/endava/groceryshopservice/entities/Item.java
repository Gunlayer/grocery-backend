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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "size")
    private Integer size;

    public ItemResponseDTO toItemResponseDTO(){
        ItemResponseDTO itemResponseDTO = ItemResponseDTO.builder()
                .id(this.id)
                .quantity(this.quantity)
                .user_id(this.user.getId())
                .product_id(this.product.getId())
                .image(this.product.getImage())
                .price(this.product.getPrice())
                .name(this.product.getName())
                .rating(this.product.getRating())
                .description(this.product.getDescription())
                .sizeType(this.product.getSizeType())
                .size(this.size)
                .build();
        return itemResponseDTO;
    }
}