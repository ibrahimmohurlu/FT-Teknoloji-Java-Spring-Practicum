package com.example.case1.Product.Service;

import com.example.case1.Product.Entity.Product;
import com.example.case1.Product.Repository.ProductRepository;
import com.example.case1.ProductComment.Entity.ProductComment;
import com.example.case1.ProductComment.Repository.ProductCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private ProductCommentRepository productCommentRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductCommentRepository productCommentRepository) {
        this.productRepository = productRepository;
        this.productCommentRepository = productCommentRepository;
    }

    public List<ProductComment> getCommentsByProductId(Long productId) {
        Optional<Product> foundProduct = productRepository.findById(productId);
        List<ProductComment> comments;
        if (foundProduct.isPresent()) {
            comments = productCommentRepository.findByProduct(foundProduct.get());
        } else {
            throw new RuntimeException("Product doesn't exists");
        }
        return comments;
    }

    public List<ProductComment> getCommentsByProductIdBetweenDate(
            Long productId,
            LocalDate startDate,
            LocalDate endDate){
        Optional<Product> foundProduct = productRepository.findById(productId);
        List<ProductComment> comments;
        if (foundProduct.isPresent()) {
            comments = productCommentRepository.findAllByProductAndCommentDateBetween(
                    foundProduct.get(),
                    startDate,
                    endDate);
        } else {
            throw new RuntimeException("Product doesn't exists");
        }
        return comments;
    }

    public List<Product> getExpiredProducts(){
        return productRepository.findAllExpired(LocalDate.now());
    }

    public List<Product> getNotExpiredProducts(){
        return productRepository.findAllNotExpired(LocalDate.now());
    }


}
