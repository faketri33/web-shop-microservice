package org.product.entity.product.gateway;

import org.product.entity.product.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface ProductRepository extends ReactiveCrudRepository<Product, UUID> {
    Flux<Product> findByChapterId(UUID chapterId);

}
