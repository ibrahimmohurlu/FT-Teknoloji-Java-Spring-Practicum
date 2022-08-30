package com.example.case1.Product.Repository;

import com.example.case1.Product.Entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    void findAllExpired() {
        List<Product> products = productRepository.findAllExpired(LocalDate.now());
        Assertions.assertNotEquals(products.size(), 0);
        for (Product p : products) {
            System.out.println(p.toString());
        }

    }

    @Test
    void findAllNotExpired() {
        Product expiredProduct = new Product();
        expiredProduct.setName("Not expired Product!");
        expiredProduct.setPrice(0.0);
        //Creating a product that will never get expired soon.
        expiredProduct.setExpDate(LocalDate.of(9999, 1, 1));
        // saving product to database
        productRepository.save(expiredProduct);
        List<Product> notExpiredProducts = productRepository.findAllNotExpired(LocalDate.now());
        Assertions.assertEquals(notExpiredProducts.size(), 1);
        for (Product p : notExpiredProducts) {
            System.out.println(p.toString());
        }
    }
}