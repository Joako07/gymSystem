package com.springboot.gym.gymsystem.models.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.gym.gymsystem.models.dtos.ClassDto;
import com.springboot.gym.gymsystem.models.entities.ClassEntity;

@Component
public class ClassMapper {

    @Autowired
    private static ModelMapper modelMapper;

    private ClassMapper(ModelMapper modelMapper){
        ClassMapper.modelMapper= modelMapper;
    }

    //Convertir de DTO a Entidad 
    public static ClassEntity dtoToEntity(ClassDto classDto){
        ClassEntity classEntity = modelMapper.map(classDto, ClassEntity.class);
        return classEntity;
    }

    //Convertir de Entidad a DTO
    public static ClassDto entityToDto(ClassEntity classEntity){
        ClassDto classDto = modelMapper.map(classEntity, ClassDto.class);
        return classDto;
    }

}
