package org.catalog.infrastructure.categories.contoller;

import org.catalog.entity.categories.model.Categories;
import org.catalog.infrastructure.categories.gateway.CategoriesService;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/catalog")
public class CategoriesController {

    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @RequestMapping("/")
    public Flux<Categories> findAll(){
        return categoriesService.findAll();
    }

    @RequestMapping("")
    public Mono<Categories> findByName(@RequestParam("name") String name){
        return categoriesService.findByNameInstance(name);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Mono<Categories> save(@RequestPart String name, @RequestPart FilePart image){
        Categories categories = new Categories();
        categories.setName(name);
        return categoriesService.save(categories, image);
    }
}
