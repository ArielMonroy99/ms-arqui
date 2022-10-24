package com.arqui.vetstore.api;

import com.arqui.vetstore.bl.OrderBl;
import com.arqui.vetstore.bl.UserBl;
import com.arqui.vetstore.dto.OrderDto;
import com.arqui.vetstore.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserApi {
    private UserBl userBl;
    private OrderBl orderBl;

    public static final Logger logger = LoggerFactory.getLogger(UserApi.class);

    @Autowired
    public UserApi(UserBl userBl, OrderBl orderBl) {
        this.userBl = userBl;
        this.orderBl = orderBl;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Page<UserDto> getUsers(@RequestParam Integer page, @RequestParam Integer size){
        return userBl.getUsers(page, size);
    }
    @PostMapping
    public UserDto saveUser(@RequestBody UserDto userDto){
        logger.info("Saving user {}", userDto);
        return userBl.saveUser(userDto);
    }
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Integer id){
        return userBl.getUser(id);
    }
    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public UserDto updateUser(@RequestBody UserDto userDto){
        return userBl.updateUser(userDto);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public void deleteUser(@PathVariable Integer id){
        userBl.deleteUser(id);
    }

    @GetMapping("/{id}/orders")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public Page<OrderDto> getUserOrders(@PathVariable Integer id, @RequestParam Integer page, @RequestParam Integer size){
        return orderBl.getOrdersByUser(page, size, id);
    }

    @GetMapping(path = "/username")
    public UserDto getUserDto(@RequestParam String username){
        return userBl.getUserByUsername(username);
    }
}
