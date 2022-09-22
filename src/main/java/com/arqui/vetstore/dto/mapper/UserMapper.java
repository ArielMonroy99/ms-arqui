package com.arqui.vetstore.dto.mapper;

import com.arqui.vetstore.dto.UserPrincipal;
import com.arqui.vetstore.dto.entity.UserEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserPrincipal userToPrincipal(UserEntity user){
        UserPrincipal userPrincipal = new UserPrincipal();
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+ role.getRole()))
                .collect(Collectors.toList());
        userPrincipal.setUsername(user.getUsername());
        userPrincipal.setPassword(user.getPassword());
        userPrincipal.setAuthorities(authorities);
        userPrincipal.setEnabled(user.getStatus() == 1);
        return userPrincipal;
    }
}
