package com.arqui.vetstore.bl;

import com.arqui.vetstore.dao.RoleRepository;
import com.arqui.vetstore.dao.UserRepository;
import com.arqui.vetstore.dto.entity.RoleEntity;
import com.arqui.vetstore.dto.entity.UserEntity;
import com.arqui.vetstore.dto.UserDto;
import com.arqui.vetstore.dto.mapper.UserMapper;
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
        UserEntity userEntity = UserMapper.userDtoToEntity(userDto);
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
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
        Page<UserDto> userDtos = userEntities.map(UserEntity::toDto);
        logger.info("Getting users");
        return userDtos;
    }
    public UserDto getUser(Integer id){
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userEntity.toDto();
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

    public UserDto getUserByUsername(String username){
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(()->{
            throw new RuntimeException("user not found");
        });
        return userEntity.toDto();
    }
}
