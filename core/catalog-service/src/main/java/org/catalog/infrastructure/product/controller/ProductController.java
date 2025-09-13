package org.catalog.infrastructure.product.controller;

import org.catalog.entity.product.model.Product;
import org.catalog.infrastructure.product.model.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/catalog/chapter/product")
public class ProductController {


    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public Flux<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/popular")
    public Flux<Product> popular() {
        return productService.findPopularProducts();
    }

    @GetMapping("/{id}")
    public Mono<Product> findById(@PathVariable("id") String id) {
        return productService.findById(id);
    }

    @PostMapping(value = "/post")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<String> post() {
        return Mono.just("post");
    }

    @PostMapping(value = "/save",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> save(
            @RequestPart("chapterUUID") String chapterUUID,
            @RequestPart("brandUUID") String brandUUID,
            @RequestPart("name") String name,
            @RequestPart("description") String description,
            @RequestPart("price") String price,
            @RequestPart("file") List<FilePart> file
    ) {
        Product product = new Product();
        product.setId(brandUUID);
        product.setDescription(description);
        product.setName(name);
        product.setPrice(new BigDecimal(price));
        product.setChapterId(chapterUUID);

        log.info("Saving product: {}, with image - {}", product, file);
        return productService.save(product, file);
    }
}
