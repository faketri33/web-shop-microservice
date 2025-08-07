package org.product.usecase.chapter;

import org.product.entity.chapter.gateway.ChapterRepository;
import org.product.entity.chapter.model.Chapter;
import org.product.infrastructure.chapter.gateway.ChapterService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class ChapterServiceImpl implements ChapterService {

    private final ChapterRepository chapterRepository;

    public ChapterServiceImpl(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

    @Override
    public Flux<Chapter> findByCategoriesId(UUID categoriesId) {
        return chapterRepository.findByCategoriesId(categoriesId);
    }

    @Override
    public Mono<Chapter> save(Chapter chapter) {
        return chapterRepository.save(chapter);
    }
}
