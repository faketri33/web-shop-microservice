package org.product.infrastructure.categories.contoller;

import org.product.entity.categories.model.Categories;
import org.product.infrastructure.categories.gateway.CategoriesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @RequestMapping("/")
    public Flux<Categories> findAll(){
        return categoriesService.findAll();
    }
}
