package com.test.controller;

import com.test.dto.Product;
import com.test.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class which is used to
 *
 * @author Tarun Rohila
 */
@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @DeleteMapping("/products/{id}")
    public Long addProduct(@PathVariable Long id) {
        productRepository.delete(productRepository.getById(id));
        return id;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @PutMapping("/products/{id}")
    public Product addProduct(@PathVariable Long id, @RequestBody Product product) {
        Product p = productRepository.getById(id);
        p.setName(product.getName());
        p.setId(product.getId());
        return productRepository.save(p);
    }
}
