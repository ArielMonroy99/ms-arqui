package com.arqui.vetstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "item_order")
@Table(name = "item_order")
public class OrderItemEntity {

    @EmbeddedId
    private OrderItemId id = new OrderItemId();
    @ManyToOne(optional = false)
    @MapsId("itemId")
    @JoinColumn(name = "item_id",insertable = false, updatable = false)
    private ItemEntity item;
    @ManyToOne(optional = false)
    @MapsId("orderId")
    @JoinColumn(name = "order_id",insertable = false, updatable = false)
    @JsonIgnore
    private OrderEntity order;
    @Column(name = "quantity")
    private Integer quantity;

    public OrderItemEntity(OrderItemId id, ItemEntity item, OrderEntity order, Integer quantity) {
        this.id = id;
        this.item = item;
        this.order = order;
        this.quantity = quantity;
    }

    public OrderItemEntity() {

    }

    public OrderItemId getId() {
        return id;
    }

    public void setId(OrderItemId id) {
        this.id = id;
    }

    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItemEntity{" +
                "id=" + id +
                ", item=" + item +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemEntity that = (OrderItemEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(item, that.item) && Objects.equals(order, that.order) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, order, quantity);
    }
}
