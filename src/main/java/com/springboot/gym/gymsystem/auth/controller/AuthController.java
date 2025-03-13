package com.springboot.gym.gymsystem.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.gym.gymsystem.auth.models.requests.LoginRequest;
import com.springboot.gym.gymsystem.auth.models.requests.RegisterRequest;
import com.springboot.gym.gymsystem.auth.models.responses.AuthResponse;
import com.springboot.gym.gymsystem.auth.services.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequesty) {
        return ResponseEntity.ok(authService.login(loginRequesty));
    }
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) { 
        return ResponseEntity.ok(authService.register(registerRequest));
    }
    

}
