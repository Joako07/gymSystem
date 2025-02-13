package com.springboot.gym.gymsystem.models.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.gym.gymsystem.models.dtos.CoachDto;
import com.springboot.gym.gymsystem.models.entities.CoachEntity;

@Component
public class CoachMapper {

    @Autowired
    private static ModelMapper modelMapper;

    private CoachMapper (ModelMapper modelMapper){
        CoachMapper.modelMapper = modelMapper;
    }

    //Convierto de DTO a entidad 
    public static CoachEntity dtoToEntity(CoachDto coachDto){
        CoachEntity coachEntity = modelMapper.map(coachDto, CoachEntity.class);
        return coachEntity;
    }

    //Convierto de entidad a DTO
    public static CoachDto entityToDto(CoachEntity coachEntity){
        CoachDto coachDto = modelMapper.map(coachEntity, CoachDto.class);
        return coachDto;
    }

}
