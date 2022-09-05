package com.example.case1.ProductComment.Repository;

import com.example.case1.Product.Entity.Product;
import com.example.case1.Product.Repository.ProductRepository;
import com.example.case1.ProductComment.Entity.ProductComment;
import com.example.case1.User.Entity.User;
import com.example.case1.User.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ProductCommentRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCommentRepository productCommentRepository;
    @Autowired
    private UserRepository userRepository;

    // product and user objects will be used to create product comment object.
    private Product product;
    private User user;
    private ProductComment comment;

    @BeforeEach
    void setUp() {
        //creating product and user objects and saving to database.
        //These objects must be present in the database to create productComment.
        product = new Product();
        product.setName("product");
        product.setPrice(11.99);
        product.setExpDate(LocalDate.of(2020, 5, 23));

        user = new User();
        user.setFirstName("ibrahim");
        user.setLastName("mohurlu");
        user.setEmail("him@gmail.com");
        user.setPhoneNumber("999-123-1234");

        comment = new ProductComment();
        comment.setComment("This is an awesome product!");
        comment.setCommentDate(LocalDate.now());
        comment.setProduct(product);
        comment.setUser(user);

        //saving objects to database.
        userRepository.save(user);
        productRepository.save(product);
        productCommentRepository.save(comment);

    }

    @Test
    void findByProduct() {
        List<ProductComment> foundComments = productCommentRepository.findByProduct(product);
        assertThat(foundComments.size() > 0);
    }

    @Test
    void findByUser() {
        List<ProductComment> foundComments = productCommentRepository.findByUser(user);
        assertThat(foundComments.size()>0);
    }

    @Test
    void findAllByProductAndCommentDateBetween() {
        //testing between 3 months before and after current date.
        List<ProductComment> foundComments = productCommentRepository.findAllByProductAndCommentDateBetween(
                product,
                LocalDate.now().minusMonths(3L),
                LocalDate.now().plusMonths(3L)
        );
        assertThat(foundComments.size()>0);
    }

    @Test
    void findAllByUserAndCommentDateBetween() {
        //testing between 3 months before and after current date.
        List<ProductComment> foundComments = productCommentRepository.findAllByUserAndCommentDateBetween(
                user,
                LocalDate.now().minusMonths(3L),
                LocalDate.now().plusMonths(3L)
        );
        assertThat(foundComments.size()>0);

    }
}