package org.catalog.usecase.categories;

import org.catalog.entity.categories.gateway.CategoriesRepository;
import org.catalog.entity.categories.model.Categories;
import org.catalog.infrastructure.categories.gateway.CategoriesService;
import org.shared.image.gateway.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    private static final Logger log = LoggerFactory.getLogger(CategoriesServiceImpl.class);
    private final CategoriesRepository categoriesRepository;
    private final ImageService imageService;

    public CategoriesServiceImpl(CategoriesRepository categoriesRepository, ImageService imageService) {
        this.categoriesRepository = categoriesRepository;
        this.imageService = imageService;
    }

    @Override
    public Flux<Categories> findAll() {
        return categoriesRepository.findAll();
    }

    @Override
    public Mono<Categories> save(Categories categories, FilePart filePart) {
        log.info("Saving category: {}", categories.getName());
        return imageService.uploadImage(filePart.filename(), filePart)
                .flatMap(f -> {
                    categories.setImage(f);
                    return categoriesRepository.save(categories);
                })
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
