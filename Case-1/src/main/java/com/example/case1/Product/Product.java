package com.example.case1.Product;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "product")
@Table
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "name",
            length = 50,
            nullable = false
    )
    private String name;

    @Column(
            name = "price",
            nullable = false,
            columnDefinition = "real"
    )
    private Double price;

    @Column(
            name = "expiration_date",
            nullable = true,
            columnDefinition = "date"
    )
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private LocalDate expDate;

    public Product() {
    }

    public Product(String name, Double price, LocalDate expDate) {
        this.name = name;
        this.price = price;
        this.expDate = expDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }
}
