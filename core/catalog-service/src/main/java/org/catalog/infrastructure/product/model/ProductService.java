package org.catalog.infrastructure.product.model;

import org.catalog.entity.product.model.Product;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductService {

    Flux<Product> findAll();

    Mono<Product> findById(String id);

    Flux<Product> findPopularProducts();
    Flux<Product> findByChapterId(String chapterId);

    Mono<Product> save(Product p, List<FilePart> file);
}
