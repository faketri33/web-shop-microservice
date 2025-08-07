package org.product.infrastructure.product.model;

import org.product.entity.product.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProductService {

    Flux<Product> findAll();

    Mono<Product> findById(UUID id);

    Flux<Product> findPopularProducts();
    Flux<Product> findByChapterId(UUID chapterId);

    Mono<Product> save(Product p);
}
