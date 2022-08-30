package com.example.case1.ProductComment.Repository;

import com.example.case1.Product.Entity.Product;
import com.example.case1.ProductComment.Entity.ProductComment;
import com.example.case1.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {

    List<ProductComment> findByUser(User user);

    List<ProductComment> findByProduct(Product product);

    List<ProductComment> findAllByProductAndCommentDateBetween(Product product, Date startDate, Date endDate);

    List<ProductComment> findAllByUserAndCommentDateBetween(User user, Date startDate, Date endDate);


}
