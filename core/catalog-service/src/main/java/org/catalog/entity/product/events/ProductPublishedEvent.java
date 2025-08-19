package org.catalog.entity.product.events;

import org.springframework.context.ApplicationEvent;

public class ProductPublishedEvent extends ApplicationEvent {

    private final String token = "ProductPublished";
    private final String message;

    public ProductPublishedEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }
}
