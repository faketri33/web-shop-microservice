package org.product.usecase.categories;

import java.util.UUID;

import org.product.entity.categories.gateway.CategoriesRepository;
import org.product.entity.categories.model.Categories;
import org.product.infrastructure.categories.gateway.CategoriesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    private static final Logger log = LoggerFactory.getLogger(CategoriesServiceImpl.class);
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
        log.info("Saving category: {}", categories.getName());
        return categoriesRepository.findByName(categories.getName()).switchIfEmpty(save(categories))
            .doOnError(Throwable.class, e -> log.error("Error saving category: {}", categories.getName(), e))
            .doOnSuccess(savedCategory -> log.info("Category saved successfully: {}", savedCategory.getName()));
    }


    @Override
    public Mono<Categories> findById(UUID id) {
        return categoriesRepository.findById(id);
    }


    @Override
    public Mono<Categories> findByName(String name) {
        return categoriesRepository.findByName(name);
    }
}
