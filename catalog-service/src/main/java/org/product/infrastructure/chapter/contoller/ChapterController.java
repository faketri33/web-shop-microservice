package org.product.infrastructure.chapter.contoller;

import org.product.entity.chapter.model.Chapter;
import org.product.infrastructure.chapter.gateway.ChapterService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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
}
