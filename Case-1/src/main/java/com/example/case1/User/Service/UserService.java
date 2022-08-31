package com.example.case1.User.Service;

import com.example.case1.ProductComment.Entity.ProductComment;
import com.example.case1.ProductComment.Repository.ProductCommentRepository;
import com.example.case1.User.Entity.User;
import com.example.case1.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private ProductCommentRepository productCommentRepository;
    private UserRepository userRepository;

    @Autowired
    public UserService(ProductCommentRepository productCommentRepository, UserRepository userRepository) {
        this.productCommentRepository = productCommentRepository;
        this.userRepository = userRepository;
    }

    public List<ProductComment> getCommentsByUserId(Long userId) {
        Optional<User> foundUser = userRepository.findById(userId);
        List<ProductComment> comments;
        if (foundUser.isPresent()) {
            comments = productCommentRepository.findByUser(foundUser.get());
        } else {
            throw new RuntimeException("User doesn't exists.");
        }
        return comments;
    }

    public List<ProductComment> getCommentsByUserIdBetweenDates(
            Long userId,
            LocalDate startDate,
            LocalDate endDate) {

        Optional<User> foundUser = userRepository.findById(userId);
        List<ProductComment> comments;
        if (foundUser.isPresent()) {
            comments = productCommentRepository.findAllByUserAndCommentDateBetween(
                    foundUser.get(),
                    startDate,
                    endDate);
        } else {
            throw new RuntimeException("User doesn't exists.");
        }
        return comments;
    }
}
