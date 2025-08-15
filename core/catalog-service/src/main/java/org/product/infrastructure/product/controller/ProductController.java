package org.product.infrastructure.product.controller;

import org.product.entity.product.model.Product;
import org.product.infrastructure.product.model.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/catalog/chapter/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/list")
    public Flux<Product> findAll() {
        return productService.findAll();
    }

    @RequestMapping("/popular")
    public Flux<Product> popular() {
        return productService.findPopularProducts();
    }

    @RequestMapping("/{id}")
    public Mono<Product> findById(@PathVariable("id") UUID id) {
        return productService.findById(id);
    }
}
