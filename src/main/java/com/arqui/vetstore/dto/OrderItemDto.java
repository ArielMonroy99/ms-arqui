package com.arqui.vetstore.dto;

public class OrderItemDto {
    ItemDto item;
    Integer quantity;
    public OrderItemDto() {
    }

    public ItemDto getItem() {
        return item;
    }

    public void setItem(ItemDto item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItemDto{" +
                "item=" + item +
                ", quantity=" + quantity +
                '}';
    }
}
