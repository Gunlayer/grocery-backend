package com.endava.groceryshopservice.entities;

import com.endava.groceryshopservice.entities.dto.ReviewForProductDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "T_REVIEWS")
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @SequenceGenerator(
            name = "grocery_shop_sequence",
            sequenceName = "grocery_shop_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grocery_shop_sequence")
    @Column(name = "review_id")
    private long reviewId;

    @Column(name = "comment_body")
    private String commentBody;

    @Column(name = "rating")
    private int rating;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    private Product product;

    public Review(ReviewForProductDTO review) {
        this.commentBody = review.getCommentBody();
        this.rating = review.getRating();
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", commentBody='" + commentBody + '\'' +
                ", rating=" + rating +
                ", user=" + user +
                ", product=" + product +
                '}';
    }
}