package org.product.usecase.categories;

import org.product.entity.categories.gateway.CategoriesRepository;
import org.product.entity.categories.model.Categories;
import org.product.infrastructure.categories.gateway.CategoriesService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CategoriesServiceImpl implements CategoriesService {

    private final CategoriesRepository categoriesRepository;

    public CategoriesServiceImpl(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }


    @Override
    public Flux<Categories> findAll() {
        return categoriesRepository.findAll();
    }

    @Override
    public Mono<Categories> save(Categories categories) {
        return categoriesRepository.save(categories);
    }
}
