package org.catalog.usecase.product;

import org.catalog.entity.product.events.ProductPublishedEvent;
import org.catalog.entity.product.events.ProductViewEvent;
import org.catalog.entity.product.gateway.ProductRepository;
import org.catalog.entity.product.model.Product;
import org.catalog.infrastructure.product.model.ProductService;
import org.shared.image.gateway.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Range;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ApplicationEventPublisher eventPublisher;
    private final ProductRepository productService;
    private final ImageService imageService;
    private final ReactiveRedisTemplate<String, String> redisTemplate;

    private static final String PRODUCT_VIEW_KEY = "product:view:ranking";

    public ProductServiceImpl(ApplicationEventPublisher eventPublisher, ProductRepository productService, ImageService imageService, ReactiveRedisTemplate<String, String> redisTemplate) {
        this.eventPublisher = eventPublisher;
        this.productService = productService;
        this.imageService = imageService;
        this.redisTemplate = redisTemplate;
    }

    public Flux<Product> findAll() {
        log.info("Find all products");
        return productService.findAll();
    }

    public Flux<Product> findPopularProducts() {
        return redisTemplate.opsForZSet()
                .reverseRange(PRODUCT_VIEW_KEY,
                        Range.from(Range.Bound.inclusive(0L)).to(Range.Bound.inclusive(99L)))
                .map(UUID::fromString)
                .collectList()
                .flatMapMany(productService::findAllById);
    }

    @Override
    public Flux<Product> findByChapterId(UUID chapterId) {
        return productService.findByChapterId(chapterId);
    }


    @Override
    public Mono<Product> findById(UUID id) {
        return productService.findById(id)
                .doOnSuccess((p) -> {
                    eventPublisher.publishEvent(new ProductViewEvent(this, id));
                    log.info("Send ProductViewEvent message to kafka for {}", id);
                });
    }

    public Mono<Product> save(Product p, List<FilePart> files) {
        return Flux.fromIterable(files)
                .flatMap(file -> {
                    String key = UUID.randomUUID() + "-" + file.filename();
                    return imageService.uploadImage(key, file)
                            .doOnError(err -> log.error("Upload failed for {}", key, err));
                })
                .collectList()
                .flatMap(images -> {
                    p.setImages(images);
                    log.info("Saving product {} with images {}", p.getName(), images);
                    return productService.save(p)
                            .doOnError(err -> log.error("Product save failed", err));
                })
                .doOnSuccess(savedProduct -> {
                    eventPublisher.publishEvent(new ProductPublishedEvent(this, savedProduct.getName()));
                    log.info("Send ProductPublishedEvent message to kafka for {}", p.getName());
                })
                .onErrorResume(err -> {
                    log.error("Save chain failed", err);
                    return Mono.empty();
                });
    }
}