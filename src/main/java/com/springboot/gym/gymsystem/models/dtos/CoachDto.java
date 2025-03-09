package com.springboot.gym.gymsystem.models.dtos;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "El nombre del coach no puede estar vacio")
    private String name;

    @NotBlank(message = "El apellido del coach no puede estar vacio")
    private String lastName;

}
