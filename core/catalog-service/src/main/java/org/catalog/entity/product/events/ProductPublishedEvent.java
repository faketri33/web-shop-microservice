package org.catalog.entity.product.events;

import org.springframework.context.ApplicationEvent;

public class ProductPublishedEvent extends ApplicationEvent {

    private static final String TOKEN = "ProductPublished";
    private final String message;

    public ProductPublishedEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getToken() {
        return TOKEN;
    }

    public String getMessage() {
        return message;
    }
}
