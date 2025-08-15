package org.product.entity.productImage.gateway;

import java.util.UUID;

import org.product.entity.productImage.model.ProductImage;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductImageRepository extends ReactiveCrudRepository<ProductImage, UUID> {}