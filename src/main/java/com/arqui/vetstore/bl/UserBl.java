package com.arqui.vetstore.bl;

import com.arqui.vetstore.dao.RoleRepository;
import com.arqui.vetstore.dao.UserRepository;
import com.arqui.vetstore.dto.entity.RoleEntity;
import com.arqui.vetstore.dto.entity.UserEntity;
import com.arqui.vetstore.dto.UserDto;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@CrossOrigin(origins = "*")
public class UserBl {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    public static final Logger logger = LoggerFactory.getLogger(UserBl.class);
    @Autowired
    public UserBl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto saveUser(UserDto userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.getName());
        userEntity.setLastname(userDto.getLastname());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPhone(userDto.getPhone());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setUsername(userDto.getUsername());
        userEntity.setStatus(1);
        userEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userEntity.setRoles(userDto.getRole().stream().map(
                role -> {
                    return roleRepository.findByRole(role);
                }).collect(Collectors.toList()));
        userEntity = userRepository.save(userEntity);
        userDto.setId(userEntity.getId());
        return userDto;
    }

    public Page<UserDto> getUsers(Integer page, Integer size){
        Page<UserEntity> userEntities = userRepository.findAll(PageRequest.of(page, size));
        Page<UserDto> userDtos = userEntities.map(userEntity -> {
            UserDto userDto = new UserDto();
            userDto.setId(userEntity.getId());
            userDto.setName(userEntity.getName());
            userDto.setLastname(userEntity.getLastname());
            userDto.setEmail(userEntity.getEmail());
            userDto.setPassword(userEntity.getPassword());
            userDto.setPhone(userEntity.getPhone());
            userDto.setUsername(userEntity.getUsername());
            return userDto;
        });
        logger.info("Getting users");
        return userDtos;
    }
    public UserDto getUser(Integer id){
        UserEntity userEntity = userRepository.findById(id).get();
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setName(userEntity.getName());
        userDto.setLastname(userEntity.getLastname());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPhone(userEntity.getPhone());
        userDto.setPassword(userEntity.getPassword());
        userDto.setUsername(userEntity.getUsername());
        userDto.setRole(userEntity.getRoles().stream().map(
                RoleEntity::getRole
        ).collect(Collectors.toList()));
        logger.info("Getting user {} ",userEntity);
        return userDto;
    }
    public UserDto updateUser(UserDto userDto){
        UserEntity userEntity = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new RuntimeException("User not found"));
        userEntity.setName(userDto.getName());
        userEntity.setLastname(userDto.getLastname());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPhone(userDto.getPhone());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setStatus(1);
        userEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userEntity = userRepository.save(userEntity);
        userDto.setId(userEntity.getId());
        return userDto;
    }
    public void deleteUser(Integer id){
        UserEntity userEntity = userRepository.findById(id).get();
        userEntity.setStatus(0);
        userRepository.save(userEntity);
    }
}
