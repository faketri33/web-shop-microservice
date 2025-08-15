package org.product.usecase.chapter;

import org.product.entity.chapter.gateway.ChapterRepository;
import org.product.entity.chapter.model.Chapter;
import org.product.infrastructure.chapter.gateway.ChapterService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ChapterServiceImpl implements ChapterService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ChapterServiceImpl.class);
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
        log.info("Saving chapter: {}", chapter.getName());
        return chapterRepository.findByName(chapter.getName())
            .switchIfEmpty(save(chapter))
            .doOnError(Throwable.class, e -> log.error("Error saving chapter: {}", chapter.getName(), e))
            .doOnSuccess(savedChapter -> log.info("Chapter saved successfully: {}", savedChapter.getName()));
    }

    @Override
    public Mono<Chapter> findById(UUID id) {
        return chapterRepository.findById(id);
    }

    @Override
    public Mono<Chapter> findByName(String name) {
        return chapterRepository.findByName(name);
    }
}
