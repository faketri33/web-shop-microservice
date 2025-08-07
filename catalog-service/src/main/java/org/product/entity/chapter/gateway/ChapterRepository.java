package org.product.entity.chapter.gateway;

import org.product.entity.chapter.model.Chapter;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface ChapterRepository extends ReactiveCrudRepository<Chapter, UUID> {
    Flux<Chapter> findByCategoriesId(UUID categoriesId);

}
