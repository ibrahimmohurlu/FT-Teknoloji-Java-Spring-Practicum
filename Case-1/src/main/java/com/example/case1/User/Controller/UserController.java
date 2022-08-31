package com.example.case1.User.Controller;

import com.example.case1.ProductComment.Entity.ProductComment;
import com.example.case1.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<ProductComment>> getCommentsByUserId(
            @PathVariable String id,
            @DateTimeFormat(pattern = "dd-mm-yyyy") @RequestParam(required = false, defaultValue = "") String start_date,
            @DateTimeFormat(pattern = "dd-mm-yyyy") @RequestParam(required = false, defaultValue = "") String end_date
    ) {
        Long userId = Long.parseLong(id);
        List<ProductComment> comments = userService.getCommentsByUserId(
                userId,
                start_date,
                end_date
        );
        return ResponseEntity.ok(comments);
    }
}
