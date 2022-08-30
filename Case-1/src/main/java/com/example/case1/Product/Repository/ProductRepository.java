package com.example.case1.Product.Repository;

import com.example.case1.Product.Entity.Product;
import com.example.case1.ProductComment.Entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    @Query(value = "SELECT p FROM product p WHERE p.expDate < :today")
    List<Product> findAllExpired(@Param("today")Date today);

    @Query("SELECT p FROM product p WHERE (p.expDate >= :today OR p.expDate IS NULL)")
    List<Product> findAllNotExpired(@Param("today") Date today);
}
