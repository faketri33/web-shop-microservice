package org.catalog.entity.product.events;

import org.springframework.context.ApplicationEvent;

import java.util.UUID;

public class ProductViewEvent extends ApplicationEvent {

    private final String token = "ProductView";
    private final String productId;

    public ProductViewEvent(Object source, UUID id) {
        super(source);
        this.productId = id.toString();
    }

    public String getToken() {
        return token;
    }

    public String getProductId() {
        return productId;
    }
}
