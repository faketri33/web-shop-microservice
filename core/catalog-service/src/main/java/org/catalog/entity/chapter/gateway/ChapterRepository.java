package org.catalog.entity.chapter.gateway;

import org.catalog.entity.chapter.model.Chapter;
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
public class ChapterRepository {

    private final ReactiveElasticsearchOperations operations;
    private final IndexCoordinates index = IndexCoordinates.of("chapters");

    public ChapterRepository(ReactiveElasticsearchOperations operations) {
        this.operations = operations;
    }

    public Mono<Chapter> findById(UUID id) {
        return operations.get(id.toString(), Chapter.class);
    }

    public Mono<Chapter> findByName(String name) {
        if (name == null || name.isEmpty())
            return Mono.empty();

        Criteria criteria = new Criteria("name").is(name);
        Query query = new CriteriaQuery(criteria);

        return operations.search(query, Chapter.class, index).next().map(SearchHit::getContent);
    }

    public Flux<Chapter> findByCategoriesId(UUID categoriesId) {
        if (categoriesId == null)
            return Flux.empty();

        Criteria criteria = new Criteria("categories_id").is(categoriesId);
        Query query = new CriteriaQuery(criteria);

        return operations.search(query, Chapter.class, index).map(SearchHit::getContent);
    }

    public Mono<Chapter> save(Chapter chapter) {
        if (chapter.getId() == null || chapter.getId().isEmpty()) chapter.setId(UUID.randomUUID().toString());
        return operations.save(chapter);
    }

}
