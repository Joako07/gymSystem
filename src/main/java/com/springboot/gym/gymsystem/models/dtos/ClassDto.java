package com.springboot.gym.gymsystem.models.dtos;

import java.time.LocalTime;
import java.util.Set;

import com.springboot.gym.gymsystem.models.enums.DaysEnum;

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
public class ClassDto {

    private Long id;

    @NotBlank(message = "El nombre de la actividad no puede estar vacio")
    @Size (min = 3, message =  "El nombre de la actividad debe tener al menos 3 caracteres")
    private String activity;

    @NotEmpty(message = "Debes eleguir al menos un día de la semana")
    private Set<DaysEnum> days;

    @NotNull(message = "La hora de inicio no puede ser nula")
    private LocalTime startClass;

    @NotNull(message = "La hora en que termina la clase no puede ser nula")
    private LocalTime endTime;

    private Set<ClientDto> clients;
}
