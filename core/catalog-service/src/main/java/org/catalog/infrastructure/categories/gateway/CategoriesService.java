package org.catalog.infrastructure.categories.gateway;

import org.catalog.entity.categories.model.Categories;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


public interface CategoriesService {
    
    Mono<Categories> findById(UUID id);

    Flux<Categories> searchByName(String name);
    Mono<Categories> findByNameInstance(String name);
    Flux<Categories> findAll();
    Mono<Categories> save(Categories categories);
}
