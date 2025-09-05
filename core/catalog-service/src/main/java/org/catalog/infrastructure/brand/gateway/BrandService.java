package org.catalog.infrastructure.brand.gateway;

import org.catalog.entity.brand.model.Brand;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BrandService {
    Flux<Brand> findAll();
    Mono<Brand> findById(String id);
    Mono<Brand> save(Brand brand, FilePart filePart);
}
