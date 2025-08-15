package org.product.infrastructure.categories.gateway;

import java.util.UUID;

import org.product.entity.categories.model.Categories;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface CategoriesService {
    
    Mono<Categories> findById(UUID id);
    Mono<Categories> findByName(String name);
    Flux<Categories> findAll();
    Mono<Categories> save(Categories categories);
}
