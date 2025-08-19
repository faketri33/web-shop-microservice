package org.catalog.infrastructure.chapter.gateway;

import org.catalog.entity.chapter.model.Chapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ChapterService {
    Mono<Chapter> findById(UUID id);
    Mono<Chapter> findByName(String name);
    Flux<Chapter> findByCategoriesId(UUID categoriesId);
    Mono<Chapter> save(Chapter chapter);
}
