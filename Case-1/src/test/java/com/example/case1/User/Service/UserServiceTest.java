package com.example.case1.User.Service;

import com.example.case1.Product.Entity.Product;
import com.example.case1.ProductComment.Entity.ProductComment;
import com.example.case1.ProductComment.Repository.ProductCommentRepository;
import com.example.case1.User.Entity.User;
import com.example.case1.User.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;

@SpringBootTest
class UserServiceTest {
    private final User mockUser = new User();
    @Mock
    private ProductCommentRepository productCommentRepository;
    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        mockUser.setFirstName("ibrahim");
        mockUser.setLastName("mohurlu");
        mockUser.setEmail("him@mail.com");
        mockUser.setPhoneNumber("123-9876-123");
        mockUser.setId(5L);

        Product returnProduct = new Product();
        returnProduct.setId(11L);
        returnProduct.setName("Expensive Mock Product!");
        returnProduct.setPrice(469.89);
        returnProduct.setExpDate(LocalDate.now().plusMonths(3L));

        ProductComment returnComment = new ProductComment();
        returnComment.setId(2L);
        returnComment.setComment("Mock product is very expensive im not buying!");
        returnComment.setCommentDate(LocalDate.now());
        returnComment.setProduct(returnProduct);
        returnComment.setUser(mockUser);

        Mockito.when(userRepository.findById(5L)).thenReturn(Optional.of(mockUser));
        Mockito.when(productCommentRepository.findByUser(mockUser)).thenReturn(List.of(returnComment));
        Mockito.when(productCommentRepository.findAllByUserAndCommentDateBetween(
                mockUser,
                LocalDate.now().minusMonths(3L),
                LocalDate.now().plusMonths(3L)
        )).thenReturn(List.of(returnComment));

        userService = new UserService(productCommentRepository, userRepository);
    }

    @Test
    void getCommentsByUserId() {
        userService.getCommentsByUserId(5L, "", "");
        //verify method calls
        verify(userRepository).findById(5L);
        verify(productCommentRepository).findByUser(mockUser);


    }

    @Test
    void getCommentsByUserIdBetweenDates() {
        userService.getCommentsByUserId(
                5L,
                LocalDate.now().minusMonths(3L).toString(),
                LocalDate.now().plusMonths(3L).toString());
        //verify method calls
        verify(productCommentRepository).findAllByUserAndCommentDateBetween(
                mockUser,
                LocalDate.now().minusMonths(3L),
                LocalDate.now().plusMonths(3L)
        );
    }
}
