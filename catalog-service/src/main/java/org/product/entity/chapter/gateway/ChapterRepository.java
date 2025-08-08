package org.product.entity.chapter.gateway;

import org.product.entity.chapter.model.Chapter;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ChapterRepository extends ReactiveCrudRepository<Chapter, UUID> {
    Mono<Chapter> findByName(String name);
    Flux<Chapter> findByCategoriesId(UUID categoriesId);

}
