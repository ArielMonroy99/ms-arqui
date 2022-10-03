package com.arqui.vetstore.bl;

import com.arqui.vetstore.dao.AddressRepository;
import com.arqui.vetstore.dao.ItemRepository;
import com.arqui.vetstore.dao.OrderRepository;
import com.arqui.vetstore.dao.UserRepository;
import com.arqui.vetstore.dto.AddressDto;
import com.arqui.vetstore.dto.OrderDto;
import com.arqui.vetstore.dto.UserDto;
import com.arqui.vetstore.dto.entity.OrderEntity;
import com.arqui.vetstore.dto.entity.OrderItemEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
            orderItemEntity.setOrder(orderEntity);
            orderItemEntity.setItem(itemRepository.findById(orderItem.getItem().getId()).orElseThrow(() -> new RuntimeException("Item not found")));
            orderItemEntity.setQuantity(orderItem.getQuantity());
            return orderItemEntity;
        }).collect(Collectors.toList()));
        logger.info("Saving order {}", orderEntity);
        OrderEntity savedOrder = orderRepository.saveAndFlush(orderEntity);
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
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderEntity.getId());
        orderDto.setDate(orderEntity.getDate());
        orderDto.setTotal(orderEntity.getTotal());
        UserDto userDto = new UserDto();
        userDto.setId(orderEntity.getUser().getId());
        userDto.setName(orderEntity.getUser().getName());
        userDto.setLastname(orderEntity.getUser().getLastname());
        userDto.setEmail(orderEntity.getUser().getEmail());
        userDto.setPhone(orderEntity.getUser().getPhone());
        userDto.setUsername(orderEntity.getUser().getUsername());
        orderDto.setUser(userDto);
        AddressDto addressDto = new AddressDto();
        addressDto.setId(orderEntity.getAddress().getId());
        addressDto.setAddress(orderEntity.getAddress().getAddress());
        addressDto.setLatitude(orderEntity.getAddress().getLatitude());
        addressDto.setLongitude(orderEntity.getAddress().getLongitude());
        orderDto.setAddress(addressDto);
        return orderDto;
    }
    public Page<OrderDto> getOrdersByUser(Integer page, Integer size ,Integer userId) {
        Page<OrderEntity> orderEntities = orderRepository.findAllByUserIdAndStatus(PageRequest.of(page, size), userId, 1);
        return orderEntities.map(orderEntity -> {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(orderEntity.getId());
            orderDto.setDate(orderEntity.getDate());
            orderDto.setTotal(orderEntity.getTotal());
            UserDto userDto = new UserDto();
            userDto.setId(orderEntity.getUser().getId());
            userDto.setName(orderEntity.getUser().getName());
            userDto.setLastname(orderEntity.getUser().getLastname());
            userDto.setEmail(orderEntity.getUser().getEmail());
            userDto.setPhone(orderEntity.getUser().getPhone());
            userDto.setUsername(orderEntity.getUser().getUsername());
            orderDto.setUser(userDto);
            AddressDto addressDto = new AddressDto();
            addressDto.setId(orderEntity.getAddress().getId());
            addressDto.setAddress(orderEntity.getAddress().getAddress());
            addressDto.setLatitude(orderEntity.getAddress().getLatitude());
            addressDto.setLongitude(orderEntity.getAddress().getLongitude());
            orderDto.setAddress(addressDto);

            return orderDto;
        });
    }

    public Page<OrderDto> getOrders(Integer page, Integer size ,Integer userId) {
        Page<OrderEntity> orderEntities = orderRepository.findAll(PageRequest.of(page, size));
        return orderEntities.map(orderEntity -> {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(orderEntity.getId());
            orderDto.setDate(orderEntity.getDate());
            orderDto.setTotal(orderEntity.getTotal());
            UserDto userDto = new UserDto();
            userDto.setId(orderEntity.getUser().getId());
            userDto.setName(orderEntity.getUser().getName());
            userDto.setLastname(orderEntity.getUser().getLastname());
            userDto.setEmail(orderEntity.getUser().getEmail());
            userDto.setPhone(orderEntity.getUser().getPhone());
            userDto.setUsername(orderEntity.getUser().getUsername());
            orderDto.setUser(userDto);
            AddressDto addressDto = new AddressDto();
            addressDto.setId(orderEntity.getAddress().getId());
            addressDto.setAddress(orderEntity.getAddress().getAddress());
            addressDto.setLatitude(orderEntity.getAddress().getLatitude());
            addressDto.setLongitude(orderEntity.getAddress().getLongitude());
            orderDto.setAddress(addressDto);
            return orderDto;
        });
    }
}

