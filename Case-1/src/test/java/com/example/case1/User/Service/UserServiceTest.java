package com.example.case1.User.Service;

import com.example.case1.ProductComment.Entity.ProductComment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void getCommentsByUserId() {
        List<ProductComment> comments = userService.getCommentsByUserId(3L,"","");
        Assertions.assertNotEquals(comments.size(), 0);
        for (ProductComment c : comments) {
            System.out.println(c.toString());
        }

    }

    @Test
    void getCommentsByUserIdBetweenDates() {
        List<ProductComment> comments = userService.getCommentsByUserId(
                4L,
                "2021-01-01",
                "2022-12-31");
        Assertions.assertNotEquals(comments.size(), 0);
        for (ProductComment c : comments) {
            System.out.println(c.toString());
        }
    }
}