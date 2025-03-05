package com.springboot.gym.gymsystem.models.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.gym.gymsystem.models.dtos.ClientDto;
import com.springboot.gym.gymsystem.models.entities.ClientEntity;

@Component
public class ClientMapper {

     @Autowired
    private static ModelMapper modelMapper;

    private ClientMapper(ModelMapper modelMapper){
        ClientMapper.modelMapper= modelMapper;
    }

     //Convertir de DTO a Entidad
     public static ClientEntity dtoToEntity(ClientDto clientDto){
        ClientEntity clientEntity = modelMapper.map(clientDto, ClientEntity.class);
        return clientEntity;
     }

    // Convertir de Entidad a DTO
     public static ClientDto entityToDto(ClientEntity clientEntity){
        ClientDto clientDto = modelMapper.map(clientEntity, ClientDto.class);
        return clientDto;
     }
      

}
