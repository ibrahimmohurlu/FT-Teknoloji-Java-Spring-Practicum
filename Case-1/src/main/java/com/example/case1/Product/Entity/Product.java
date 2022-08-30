package com.example.case1.Product.Entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "product")
@Table
@Data
public class Product {
    @Id
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "price", nullable = false, columnDefinition = "real")
    private Double price;

    @Column(name = "expiration_date", nullable = true, columnDefinition = "date")
    private LocalDate expDate;

    public Product() {
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", expDate=" + expDate +
                '}';
    }
}
