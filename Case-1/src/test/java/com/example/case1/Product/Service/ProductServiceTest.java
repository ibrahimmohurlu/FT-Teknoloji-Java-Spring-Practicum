package com.example.case1.Product.Service;

import com.example.case1.Product.Entity.Product;
import com.example.case1.ProductComment.Entity.ProductComment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    void getCommentsByProductId() {
        List<ProductComment> commentList = productService.getCommentsByProductId(3L);
        Assertions.assertNotEquals(commentList.size(), 0);
        for (ProductComment c : commentList) {
            System.out.println(c.toString());
        }
    }

    @Test
    void getCommentsByProductIdBetweenDate() {
        List<ProductComment> commentList = productService.getCommentsByProductIdBetweenDate(
                8L,
                LocalDate.of(2021, 1, 1),
                LocalDate.now());
        Assertions.assertNotEquals(commentList.size(), 0);
        for (ProductComment c : commentList) {
            System.out.println(c.toString());
        }
    }

    @Test
    void getExpiredProducts() {
        List<Product> products = productService.getExpiredProducts();
        Assertions.assertNotEquals(products.size(), 0);
        for (Product p : products) {
            System.out.println(p.toString());
        }
    }

    @Test
    void getNotExpiredProducts() {
        List<Product> products = productService.getNotExpiredProducts();
        Assertions.assertNotEquals(products.size(), 0);
        for (Product p : products) {
            System.out.println(p.toString());
        }
    }
}