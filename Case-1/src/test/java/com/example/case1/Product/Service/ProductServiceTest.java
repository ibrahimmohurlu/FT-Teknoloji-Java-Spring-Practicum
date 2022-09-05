package com.example.case1.Product.Service;

import com.example.case1.Product.Entity.Product;
import com.example.case1.Product.Repository.ProductRepository;
import com.example.case1.ProductComment.Entity.ProductComment;
import com.example.case1.ProductComment.Repository.ProductCommentRepository;
import com.example.case1.User.Entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@SpringBootTest
class ProductServiceTest {
    private final Product returnProduct = new Product();
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductCommentRepository productCommentRepository;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        returnProduct.setId(11L);
        returnProduct.setName("Expensive Mock Product!");
        returnProduct.setPrice(469.89);
        returnProduct.setExpDate(LocalDate.now().plusMonths(3L));

        User mockUser = new User();
        mockUser.setFirstName("ibrahim");
        mockUser.setLastName("mohurlu");
        mockUser.setEmail("him@mail.com");
        mockUser.setPhoneNumber("123-9876-123");
        mockUser.setId(5L);

        ProductComment returnComment = new ProductComment();
        returnComment.setId(2L);
        returnComment.setComment("Mock product is very expensive im not buying!");
        returnComment.setCommentDate(LocalDate.now());
        returnComment.setProduct(returnProduct);
        returnComment.setUser(mockUser);


        Mockito.when(productRepository.findById(11L)).thenReturn(Optional.of(returnProduct));
        Mockito.when(productCommentRepository.findByProduct(returnProduct)).thenReturn(List.of(returnComment));

        productService = new ProductService(productRepository, productCommentRepository);

    }

    @Test
    void canGetCommentsByProductId() {
        productService.getCommentsByProductId(11L);
        //verify method calls in productService
        verify(productRepository).findById(11L);
        verify(productCommentRepository).findByProduct(returnProduct);

    }

    @Test
    void canGetCommentsByProductIdBetweenDate() {
        productService.getCommentsByProductIdBetweenDate(
                11L,
                LocalDate.now().minusMonths(3L).toString(),
                LocalDate.now().plusMonths(3L).toString());
        //verify method calls in productService
        verify(productRepository).findById(11L);
        verify(productCommentRepository).findAllByProductAndCommentDateBetween(
                returnProduct,
                LocalDate.now().minusMonths(3L),
                LocalDate.now().plusMonths(3L));
        //testing if else statement in the product service
        productService.getCommentsByProductIdBetweenDate(
                11L,
                "",
                "");
        verify(productCommentRepository).findByProduct(returnProduct);
    }

    @Test
    void canGetExpiredProducts() {
        productService.getExpiredProducts();
        verify(productRepository).findAllExpired(LocalDate.now());

    }

    @Test
    void getNotExpiredProducts() {
        productService.getNotExpiredProducts();
        verify(productRepository).findAllNotExpired(LocalDate.now());
    }
}