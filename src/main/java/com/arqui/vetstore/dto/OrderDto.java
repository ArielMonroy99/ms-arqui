package com.arqui.vetstore.dto;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class OrderDto {
    private Integer id;
    private BigDecimal total;
    private Timestamp date;
    private UserDto user;
    private AddressDto address;
    private Integer status;
    private List<OrderItemDto> items;
    public OrderDto() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
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
                ", status=" + status +
                ", items=" + items +
                '}';
    }
}
