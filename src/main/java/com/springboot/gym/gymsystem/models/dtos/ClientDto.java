package com.springboot.gym.gymsystem.models.dtos;

import com.springboot.gym.gymsystem.models.enums.MembershipTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private long id; 
    private String name;
    private String lastName;
    private String cellphone;
    private MembershipTypeEnum type;
}
