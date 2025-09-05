package org.catalog.usecase.brand;

import org.catalog.entity.brand.gateway.BrandRepository;
import org.catalog.entity.brand.model.Brand;
import org.catalog.infrastructure.brand.gateway.BrandService;
import org.shared.image.gateway.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BrandServiceImpl implements BrandService {

    private static final Logger log = LoggerFactory.getLogger(BrandServiceImpl.class);
    private final BrandRepository brandRepository;
    private final ImageService imageService;


    public BrandServiceImpl(BrandRepository brandRepository, ImageService imageService) {
        this.brandRepository = brandRepository;
        this.imageService = imageService;
    }

    @Override
    public Flux<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Mono<Brand> findById(String id) {
        return brandRepository.findById(id);
    }

    @Override
    public Mono<Brand> save(Brand brand, FilePart filePart) {
        return imageService
                .uploadImage(filePart.filename(), filePart)
                .flatMap(f -> {
                    brand.setImage(f);
                    log.info("Start saving brand {}", brand);
                    return brandRepository.save(brand);
                }).doOnSuccess(b -> log.info("Saving brand success {}", brand))
                .onErrorResume(ex -> {
                    log.error(ex.getMessage());
                    return Mono.empty();
                });
    }
}
