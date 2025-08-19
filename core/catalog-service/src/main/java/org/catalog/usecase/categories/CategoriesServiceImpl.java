package org.catalog.usecase.categories;

import org.catalog.entity.categories.gateway.CategoriesRepository;
import org.catalog.entity.categories.model.Categories;
import org.catalog.infrastructure.categories.gateway.CategoriesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

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
        return findByNameInstance(categories.getName()).switchIfEmpty(categoriesRepository.save(categories))
            .doOnError(Throwable.class, e -> log.error("Error saving category: {}", categories.getName(), e))
            .doOnSuccess(savedCategory -> log.info("Category saved successfully: {}", savedCategory.getName()));
    }


    @Override
    public Mono<Categories> findById(UUID id) {
        return categoriesRepository.findById(id);
    }

    @Override
    public Flux<Categories> searchByName(String name) {
        return categoriesRepository.findByName(name);
    }

    @Override
    public Mono<Categories> findByNameInstance(String name) {
        return categoriesRepository.findByNameInstance(name);
    }
}
