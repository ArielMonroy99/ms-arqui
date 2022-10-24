package com.arqui.vetstore.bl;

import com.arqui.vetstore.dao.AddressRepository;
import com.arqui.vetstore.dao.ItemRepository;
import com.arqui.vetstore.dao.OrderRepository;
import com.arqui.vetstore.dao.UserRepository;
import com.arqui.vetstore.dto.AddressDto;
import com.arqui.vetstore.dto.OrderDto;
import com.arqui.vetstore.dto.UserDto;
import com.arqui.vetstore.dto.entity.ItemEntity;
import com.arqui.vetstore.dto.entity.OrderEntity;
import com.arqui.vetstore.dto.entity.OrderItemEntity;
import com.arqui.vetstore.dto.mapper.AddressMapper;
import com.arqui.vetstore.dto.mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.stream.Collectors;

@Service
public class OrderBl {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ItemRepository itemRepository;
    public static final Logger logger = LoggerFactory.getLogger(OrderBl.class);

    @Autowired
    public OrderBl(OrderRepository orderRepository, UserRepository userRepository, AddressRepository addressRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public OrderDto saveOrder(OrderDto order){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setDate(order.getDate());
        orderEntity.setTotal(order.getTotal());
        orderEntity.setUser(userRepository.findById(order.getUser().getId()).orElseThrow(() -> new RuntimeException("User not found")));
        orderEntity.setAddress(addressRepository.findById(order.getAddress().getId()).orElseThrow(() -> new RuntimeException("Address not found")));
        orderEntity.setStatus(1);
        orderEntity.setDate(new Timestamp(System.currentTimeMillis()));
        orderEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        orderEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        orderEntity.setItems(order.getItems().stream().map(orderItem -> {
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            ItemEntity itemEntity = itemRepository.findById(orderItem.getItem().getId()).orElseThrow(() -> new RuntimeException("Item not found"));
            orderItemEntity.setOrder(orderEntity);
            orderItemEntity.setItem(itemEntity);
            orderItemEntity.setQuantity(orderItem.getQuantity());
            itemEntity.setStock(itemEntity.getStock() - orderItem.getQuantity());
            itemRepository.save(itemEntity);
            return orderItemEntity;
        }).collect(Collectors.toList()));
        logger.info("Saving order {}", orderEntity);
        OrderEntity savedOrder = orderRepository.save(orderEntity);
        order.setId(savedOrder.getId());
        return order;
    }

    public OrderDto updateOrder(OrderDto order){
        OrderEntity orderEntity = orderRepository.findById(order.getId()).orElseThrow(
                () -> new RuntimeException("Order not found")
        );
        orderEntity.setDate(order.getDate());
        orderEntity.setTotal(order.getTotal());
        orderEntity.setUser(userRepository.findById(order.getUser().getId()).orElseThrow(() -> new RuntimeException("User not found")));
        orderEntity.setAddress(addressRepository.findById(order.getAddress().getId()).orElseThrow(() -> new RuntimeException("Address not found")));
        orderEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return order;
    }

    public void deleteOrder(Integer id){
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Order not found")
        );
        orderEntity.setStatus(0);
        orderEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        orderRepository.save(orderEntity);
    }

    public OrderDto getOrder(Integer id){
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Order not found")
        );
        return OrderMapper.orderEntityToDto(orderEntity);
    }
    public Page<OrderDto> getOrdersByUser(Integer page, Integer size ,Integer userId) {
        Page<OrderEntity> orderEntities = orderRepository.findAllByUserIdAndStatus(PageRequest.of(page, size), userId, 1);
        return orderEntities.map(OrderMapper::orderEntityToDto);
    }

    public Page<OrderDto> getOrders(Integer page, Integer size ,Integer userId) {
        Page<OrderEntity> orderEntities = orderRepository.findAll(PageRequest.of(page, size));
        return orderEntities.map(OrderMapper::orderEntityToDto);
    }

    public Page<OrderDto> getOrdersByStatus(Integer page, Integer size ,Integer status) {
        Page<OrderEntity> orderEntities = orderRepository.findAllByStatusOrderByDateDesc(PageRequest.of(page, size), status);
        return orderEntities.map(OrderMapper::orderEntityToDto);
    }

    public OrderDto updateOrderStatus(Integer id, Integer status){
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Order not found")
        );
        orderEntity.setStatus(status);
        orderEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        orderRepository.save(orderEntity);
        return OrderMapper.orderEntityToDto(orderEntity);
    }

    public Page<OrderDto> getAllOrders(Integer page, Integer size) {
        Page<OrderEntity> orderEntities = orderRepository.findAll(PageRequest.of(page, size, Sort.by("date").descending().and(Sort.by("id").descending())));
        return orderEntities.map(OrderMapper::orderEntityToDto);
    }
}

