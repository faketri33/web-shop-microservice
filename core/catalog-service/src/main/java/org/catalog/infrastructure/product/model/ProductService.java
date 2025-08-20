package org.catalog.infrastructure.product.model;

import org.catalog.entity.product.model.Product;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    Flux<Product> findAll();

    Mono<Product> findById(UUID id);

    Flux<Product> findPopularProducts();
    Flux<Product> findByChapterId(UUID chapterId);

    Mono<Product> save(Product p, List<FilePart> file);
}
