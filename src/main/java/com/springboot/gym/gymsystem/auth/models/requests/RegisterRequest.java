package com.springboot.gym.gymsystem.auth.models.requests;

import com.springboot.gym.gymsystem.auth.user.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username;
    private String password;
    private Role role;

}
