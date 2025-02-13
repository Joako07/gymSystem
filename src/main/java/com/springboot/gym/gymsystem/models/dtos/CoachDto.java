package com.springboot.gym.gymsystem.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoachDto {

    private Long id;
    private String name;
    private String lastName;

}
