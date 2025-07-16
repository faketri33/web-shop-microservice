package org.product.infastructure.product.model;

import org.product.entity.product.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProductService {

    Flux<Product> findAll();
    Mono<Product> findById(UUID id);
    Flux<Product> findPopularProducts();
    Mono<Product> save(Product p);
}
