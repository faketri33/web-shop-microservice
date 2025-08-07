package org.product.entity.categories.gateway;

import org.product.entity.categories.model.Categories;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface CategoriesRepository extends ReactiveCrudRepository<Categories, UUID> {
}
