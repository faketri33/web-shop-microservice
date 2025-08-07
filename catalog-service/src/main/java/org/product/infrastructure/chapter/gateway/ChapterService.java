package org.product.infrastructure.chapter.gateway;

import org.product.entity.chapter.gateway.ChapterRepository;
import org.product.entity.chapter.model.Chapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ChapterService {
    Flux<Chapter> findByCategoriesId(UUID categoriesId);
    Mono<Chapter> save(Chapter chapter);
}
