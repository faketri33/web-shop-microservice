package org.example.entity.order.model;

import org.example.entity.orderStatus.model.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Order {

    private UUID id;
    private UUID userId;
    private OrderStatus orderStatus;
    private BigDecimal price;
    private Instant createAt = Instant.now();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(userId, order.userId) && orderStatus == order.orderStatus && Objects.equals(price, order.price) && Objects.equals(createAt, order.createAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, orderStatus, price, createAt);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderStatus=" + orderStatus +
                ", price=" + price +
                ", createAt=" + createAt +
                '}';
    }
}
