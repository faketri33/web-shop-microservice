package org.example.entity.basket.gateway;

import org.example.entity.basket.model.Basket;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BasketRepository extends CrudRepository<Basket, UUID> {
}
