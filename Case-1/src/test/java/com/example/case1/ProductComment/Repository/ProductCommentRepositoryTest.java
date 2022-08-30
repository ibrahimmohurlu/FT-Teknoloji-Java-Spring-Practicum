package com.example.case1.ProductComment.Repository;

import com.example.case1.Product.Entity.Product;
import com.example.case1.Product.Repository.ProductRepository;
import com.example.case1.ProductComment.Entity.ProductComment;
import com.example.case1.User.Entity.User;
import com.example.case1.User.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ProductCommentRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCommentRepository productCommentRepository;
    @Autowired
    private UserRepository userRepository;
    //Repositories tested on a pre-filled database
    @Test
    void findByProduct() {
        //Choose random product based on ID
        //then try to retrieve comments based on that Product object.
        Optional<Product> foundProduct = productRepository.findById(3L);
        if (foundProduct.isPresent()) {
            List<ProductComment> comments = productCommentRepository.findByProduct(foundProduct.get());
            Assertions.assertNotEquals(comments.size(), 0);
            for (ProductComment c : comments) {
                System.out.println(c.toString());
            }
        }
    }

    @Test
    void findByUser() {
        //Choose a random User by ID
        //then try to retrieve comments based on that user object
        Optional<User> foundUser = userRepository.findById(3L);
        if (foundUser.isPresent()) {
            List<ProductComment> comments = productCommentRepository.findByUser(foundUser.get());
            Assertions.assertNotEquals(comments.size(), 0);
            for (ProductComment c : comments) {
                System.out.println(c.toString());
            }
        }
    }

    @Test
    void findAllByProductAndCommentDateBetween() {
        //pick random product then try to retrieve comments between dates
        Optional<Product> foundProduct = productRepository.findById(3L);

        if (foundProduct.isPresent()) {
            List<ProductComment> comments = productCommentRepository.findAllByProductAndCommentDateBetween(
                    foundProduct.get(),
                    LocalDate.of(2021,1,1),
                    LocalDate.of(2022,12,30));
            Assertions.assertNotEquals(comments.size(), 0);
            for (ProductComment c : comments) {
                System.out.println(c.toString());
            }
        }
    }

    @Test
    void findAllByUserAndCommentDateBetween() {
        //pick random user then try to retrieve comments between dates
        Optional<User> foundUser = userRepository.findById(3L);
        if (foundUser.isPresent()) {
            List<ProductComment> comments = productCommentRepository.findAllByUserAndCommentDateBetween(
                    foundUser.get(),
                    LocalDate.of(2021,1,1),
                    LocalDate.of(2022,12,30));
            Assertions.assertNotEquals(comments.size(), 0);
            for (ProductComment c : comments) {
                System.out.println(c.toString());
            }
        }

    }
}