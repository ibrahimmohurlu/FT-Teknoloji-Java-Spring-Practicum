package com.example.case1.ProductComment.DAO;

import com.example.case1.Product.Entity.Product;
import com.example.case1.ProductComment.Entity.ProductComment;
import com.example.case1.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ProductCommentDAO extends JpaRepository<ProductComment, Long> {
    List<ProductComment> findByProduct(Product product);

    List<ProductComment> findAllByProductAndCommentDateBetween(Product product, LocalDate startDate, Date endDate);

    List<ProductComment> findByUser(User user);

    List<ProductComment> findAllByUserAndCommentDateBetween(User user, Date startDate, Date endDate);


}
