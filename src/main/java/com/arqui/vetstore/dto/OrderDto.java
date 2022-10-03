package com.arqui.vetstore.dto;

import com.arqui.vetstore.dto.entity.OrderItemEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class OrderDto {
    private Integer id;
    private BigDecimal total;
    private Timestamp date;
    private UserDto user;
    private AddressDto address;
    private List<OrderItemEntity> items;
    public OrderDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public List<OrderItemEntity> getItems() {
        return items;
    }

    public void setItems(List<OrderItemEntity> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", total=" + total +
                ", date=" + date +
                ", user=" + user +
                ", address=" + address +
                ", items=" + items +
                '}';
    }
}
