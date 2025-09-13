package org.catalog.entity.product.events;

import org.springframework.context.ApplicationEvent;


public class ProductViewEvent extends ApplicationEvent {

    private static final String TOKEN = "ProductView";
    private final String productId;

    public ProductViewEvent(Object source, String id) {
        super(source);
        this.productId = id;
    }

    public String getToken() {
        return TOKEN;
    }

    public String getProductId() {
        return productId;
    }
}
