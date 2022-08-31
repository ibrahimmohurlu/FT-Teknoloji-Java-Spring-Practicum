package com.example.case1.Product.Controller;

import com.example.case1.Product.Entity.Product;
import com.example.case1.Product.Service.ProductService;
import com.example.case1.ProductComment.Entity.ProductComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }



    @GetMapping("/{id}/comments")
    public ResponseEntity<List<ProductComment>> getCommentsByProductIdBetweenDates(
            @PathVariable String id,
            @DateTimeFormat(pattern = "dd-mm-yyyy") @RequestParam(required = false,defaultValue = "") String start_date,
            @DateTimeFormat(pattern = "dd-mm-yyyy") @RequestParam(required = false,defaultValue = "") String end_date) {
        Long productId = Long.parseLong(id);
        List<ProductComment> comments = productService.getCommentsByProductIdBetweenDate(
                productId,
                start_date,
                end_date
        );
        return ResponseEntity.ok(comments);

    }


    @GetMapping("/expired")
    public ResponseEntity<List<Product>> getExpiredProducts() {
        return ResponseEntity.ok(productService.getExpiredProducts());
    }

    @GetMapping("/not-expired")
    public ResponseEntity<List<Product>> getNotExpiredProducts() {
        return ResponseEntity.ok(productService.getNotExpiredProducts());
    }
}
