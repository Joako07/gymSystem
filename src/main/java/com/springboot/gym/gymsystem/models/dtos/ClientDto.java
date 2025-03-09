package com.springboot.gym.gymsystem.models.dtos;

import java.util.Set;

import com.springboot.gym.gymsystem.models.enums.MembershipTypeEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "El nombre del cliente no puede estar vacio")
    private String name;

    @NotBlank(message = "El apellido del cliente no puede estar vacio")
    private String lastName;

    @NotEmpty
    @Size(min = 10, message = "El número debe tener al menos 10 caracteres")
    private String cellphone;

    @NotNull(message = "Debes eleguir una membresia")
    private MembershipTypeEnum type;

    private Set<ClassDto> classes;
}
