package org.catalog.entity.product.gateway;


import org.catalog.entity.product.model.Product;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {

    private final ReactiveElasticsearchOperations operations;
    private final IndexCoordinates index = IndexCoordinates.of("products");

    public ProductRepository(ReactiveElasticsearchOperations operations) {
        this.operations = operations;
    }

    public Flux<Product> findAll() {
        return operations.search(Query.findAll(), Product.class, index)
                .map(SearchHit::getContent);
    }

    public Mono<Product> findById(UUID id) {
        return operations.get(id.toString(), Product.class, index);
    }

    public Flux<Product> findAllById(List<UUID> uuids) {
        if (uuids == null || uuids.isEmpty()) return Flux.empty();

        Criteria criteria = new Criteria("id").in(uuids.stream().map(UUID::toString).toArray());
        Query query = new CriteriaQuery(criteria);

        return operations.search(query, Product.class, index)
                .map(SearchHit::getContent);
    }

    public Flux<Product> findByChapterId(UUID chapterId) {
        Criteria criteria = new Criteria("chapter_id").is(chapterId.toString());
        Query query = new CriteriaQuery(criteria);

        return operations.search(query, Product.class, index)
                .map(SearchHit::getContent);
    }

    public Flux<Product> findProductByName(String name) {
        Criteria criteria = new Criteria("name").matches(name);
        Query query = new CriteriaQuery(criteria);

        return operations.search(query, Product.class, index)
                .map(SearchHit::getContent);
    }

    public Mono<Product> save(Product p) {
        if (p.getId() == null || p.getId().isEmpty()) p.setId(UUID.randomUUID().toString());
        return operations.save(p, index);
    }


}
