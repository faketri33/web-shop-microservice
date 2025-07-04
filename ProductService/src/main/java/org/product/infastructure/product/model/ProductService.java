package org.product.infastructure.product.model;

import org.product.entity.product.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Flux<Product> findAll();
    Mono<Product> save(Product p);
}
