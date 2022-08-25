package com.example.case1.ProductComment;

import com.example.case1.Product.Product;
import com.example.case1.User.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "product_comment")
@Table
public class ProductComment {
    @Id
    @SequenceGenerator(
            name = "product_comment_sequence",
            sequenceName = "product_comment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_comment_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "comment",
            length = 500
    )
    private String comment;

    @Column(
            name = "comment_date",
            columnDefinition = "date",
            nullable = false
    )
    private LocalDate commentDate;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product productId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User userId;
}
