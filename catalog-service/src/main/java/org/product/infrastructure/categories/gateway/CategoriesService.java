package org.product.infrastructure.categories.gateway;

import org.product.entity.categories.model.Categories;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface CategoriesService {
    Flux<Categories> findAll();
    Mono<Categories> save(Categories categories);
}
