package com.springboot.gym.gymsystem.auth.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.gym.gymsystem.auth.jwt.JwtService;
import com.springboot.gym.gymsystem.auth.models.requests.LoginRequest;
import com.springboot.gym.gymsystem.auth.models.requests.RegisterRequest;
import com.springboot.gym.gymsystem.auth.models.responses.AuthResponse;
import com.springboot.gym.gymsystem.auth.repository.UserRepository;
import com.springboot.gym.gymsystem.auth.user.Role;
import com.springboot.gym.gymsystem.auth.user.UserEntity;
import com.springboot.gym.gymsystem.exceptions.BadRequestApiException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    

    public AuthResponse login(LoginRequest loginRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException e) {
            throw BadRequestApiException.builder()
                .error(e.getMessage())
                .message("El usuario o contraseña son incorrectos")
                .build(); 
        }
        
        UserDetails userDetails = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();

        String token = jwtService.getToken(userDetails);
        return AuthResponse.builder()
        .usernam(userDetails.getUsername())
        .token(token)
        .build();
    }

    public AuthResponse register(RegisterRequest registerRequest){
        UserEntity userEntity = UserEntity.builder()
        .username(registerRequest.getUsername())
        .password(passwordEncoder.encode(registerRequest.getPassword()))
        .role(Role.ROLE_USER)
        .build();

        userRepository.save(userEntity);

        return AuthResponse.builder()
        .usernam(userEntity.getUsername())
        .token(jwtService.getToken(userEntity))
        .build();
    }

}
