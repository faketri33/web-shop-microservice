package org.catalog.entity.categories.gateway;

import io.micrometer.common.util.StringUtils;
import org.catalog.entity.categories.model.Categories;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class CategoriesRepository {
    private final ReactiveElasticsearchOperations operations;
    private final IndexCoordinates index = IndexCoordinates.of("categories");

    public CategoriesRepository(ReactiveElasticsearchOperations operations) {
        this.operations = operations;
    }

    public Mono<Categories> findById(UUID id) {
        return operations.get(id.toString(), Categories.class);
    }

    public Flux<Categories> findByName(String name) {
        if (StringUtils.isEmpty(name))
            return Flux.empty();

        Criteria criteria = new Criteria("name").matches(name);
        Query query = new CriteriaQuery(criteria);

        return operations.search(query, Categories.class, index).map(SearchHit::getContent);
    }

    public Mono<Categories> findByNameInstance(String name) {
        if (StringUtils.isEmpty(name)) {
            return Mono.empty();
        }

        Criteria criteria = new Criteria("name").is(name);
        Query query = new CriteriaQuery(criteria);

        return operations.search(query, Categories.class, IndexCoordinates.of("categories"))
                .map(SearchHit::getContent)
                .next();
    }


    public Flux<Categories> findAll() {
        return operations.search(Query.findAll(), Categories.class, index)
                .map(SearchHit::getContent);
    }

    public Mono<Categories> save(Categories categories) {
        if (categories.getId() == null || categories.getId().isEmpty()) categories.setId(UUID.randomUUID().toString());
        return operations.save(categories, index);
    }
}
