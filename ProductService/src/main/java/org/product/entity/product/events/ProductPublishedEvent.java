package org.product.entity.product.events;

import org.springframework.context.ApplicationEvent;

public class ProductPublishedEvent extends ApplicationEvent {
    private String message;

    public ProductPublishedEvent(Object source) {
        super(source);
    }

    public ProductPublishedEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
