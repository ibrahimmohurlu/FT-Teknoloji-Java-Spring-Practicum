package com.example.case1.Product.Entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

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
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private Date expDate;

    public Product() {
    }

    public Product(String name, Double price, Date expDate) {
        this.name = name;
        this.price = price;
        this.expDate = expDate;
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
