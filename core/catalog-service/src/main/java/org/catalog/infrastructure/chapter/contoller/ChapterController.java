package org.catalog.infrastructure.chapter.contoller;

import org.catalog.entity.chapter.model.Chapter;
import org.catalog.infrastructure.chapter.gateway.ChapterService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/catalog/chapter")
public class ChapterController {

    private final ChapterService chapterService;

    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @RequestMapping("/{categoriesId}")
    public Flux<Chapter> findByCategoriesId(@PathVariable UUID categoriesId){
        return chapterService.findByCategoriesId(categoriesId);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Mono<Chapter> save(@RequestBody Chapter chapter){
        return chapterService.save(chapter);
    }
}
