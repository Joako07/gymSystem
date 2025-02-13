package com.springboot.gym.gymsystem.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.gym.gymsystem.models.dtos.ClientDto;
import com.springboot.gym.gymsystem.models.entities.ClientEntity;
import com.springboot.gym.gymsystem.models.mappers.ClientMapper;
import com.springboot.gym.gymsystem.repositories.ClientRepository;
import com.springboot.gym.gymsystem.services.interfaces.ClientService;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    //CREATE
    @Override
    @Transactional
    public ClientDto creatClient(ClientDto clientDto) {
        ClientEntity clientEntity = ClientMapper.dtoToEntity(clientDto);
        clientRepository.save(clientEntity);
        return ClientMapper.entityToDto(clientEntity);
    }

    //GETALL
    @Transactional(readOnly = true)
    @Override
    public Page<ClientDto> getAllClient(int pageNumber, int sizePage, String orderBy, String sortDir) {
      Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? Sort.Direction.DESC : Sort.Direction.ASC;
      Pageable pageable = PageRequest.of(pageNumber, sizePage, Sort.by(direction, orderBy));
      return clientRepository.findAll(pageable).map(ClientMapper::entityToDto);
    }


    //GETONE
    @Override
    @Transactional(readOnly = true)
    public ClientDto getById(long id) {
      return clientRepository.findById(id)
      .map(ClientMapper::entityToDto)
      //Exepcion a personalizar -----------------------------------------
      .orElseThrow(() -> new NoSuchElementException("Class not found with id: " + id));
    }

    //UPDATE
    @Override
    @Transactional
    public ClientDto updateClient(long id, ClientDto clientDto) {
      ClientEntity clientEntity = clientRepository.findById(id)
      //Exepcion a personalizar -----------------------------------------
      .orElseThrow(() -> new NoSuchElementException("Class not found with id: " + id));
    
      clientEntity.setId(clientDto.getId());
      clientEntity.setName(clientDto.getName());
      clientEntity.setLastName(clientDto.getLastName());
      clientEntity.setCellphone(clientDto.getCellphone());
      clientEntity.setType(clientDto.getType());

      ClientEntity clientEntity2 = clientRepository.save(clientEntity);
      return ClientMapper.entityToDto(clientEntity2);
    }

    //DELETE
    @Override
    @Transactional
    public void deletClient(long id) {
        ClientEntity clientEntity = clientRepository.findById(id)
         //Exepcion a personalizar -----------------------------------------
        .orElseThrow(() -> new NoSuchElementException("Class not found with id: " + id));
        clientRepository.delete(clientEntity); 
    }
}
