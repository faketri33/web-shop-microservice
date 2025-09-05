package org.catalog.entity.brand.gateway;

import org.catalog.entity.brand.model.Brand;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class BrandRepository {
    private final ReactiveElasticsearchOperations operations;
    private final IndexCoordinates index = IndexCoordinates.of("brand");

    public BrandRepository(ReactiveElasticsearchOperations operations) {
        this.operations = operations;
    }

    public Flux<Brand> findAll(){
        return operations.search(Query.findAll(), Brand.class, index).map(SearchHit::getContent);
    }

    public Mono<Brand> findById(String id){
        return operations.get(id, Brand.class, index);
    }

    public Mono<Brand> save(Brand brand){
        return operations.save(brand, index);
    }
}
