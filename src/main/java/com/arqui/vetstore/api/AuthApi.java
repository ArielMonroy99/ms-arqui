package com.arqui.vetstore.api;

import com.arqui.vetstore.configure.jwt.JwtProvider;
import com.arqui.vetstore.configure.jwt.JwtResponse;
import com.arqui.vetstore.dto.LoginDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthApi {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider tokenProvider;

    public static final Logger logger = LoggerFactory.getLogger(AuthApi.class);

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser (@Valid @RequestBody LoginDto login){
        logger.info("Iniciando autenticación de usuario {}", login.getUsername());
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(),
                        login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return  ResponseEntity.ok(new JwtResponse(jwt));
    }
}
