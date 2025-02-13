package com.springboot.gym.gymsystem.models.dtos;

import java.time.LocalTime;
import java.util.Set;

import com.springboot.gym.gymsystem.models.enums.DaysEnum;

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
    private String activity;
    private Set<DaysEnum> days;
    private LocalTime startClass;
    private LocalTime endTime;
}
