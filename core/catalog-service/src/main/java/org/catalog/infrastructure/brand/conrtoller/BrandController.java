package org.catalog.infrastructure.brand.conrtoller;

import org.catalog.entity.brand.model.Brand;
import org.catalog.infrastructure.brand.gateway.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/catalog/brand")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @RequestMapping("/")
    public Flux<Brand> findAll() {
        return brandService.findAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Brand> save(
            @RequestPart String name,
            @RequestPart FilePart images
    ) {
        Brand brand = new Brand();
        brand.setName(name);
        return brandService.save(brand, images);
    }
}
