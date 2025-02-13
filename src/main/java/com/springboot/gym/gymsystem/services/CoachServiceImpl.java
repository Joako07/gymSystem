package com.springboot.gym.gymsystem.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.gym.gymsystem.models.dtos.CoachDto;
import com.springboot.gym.gymsystem.models.entities.CoachEntity;
import com.springboot.gym.gymsystem.models.mappers.CoachMapper;
import com.springboot.gym.gymsystem.repositories.CoachRepository;
import com.springboot.gym.gymsystem.services.interfaces.CoachService;

@Service
public class CoachServiceImpl implements CoachService{

    @Autowired
    private CoachRepository coachRepository;

    //CREATE
    @Override
    @Transactional
    public CoachDto creatCoach(CoachDto coachDto) {
       CoachEntity coachEntity = CoachMapper.dtoToEntity(coachDto);
       coachRepository.save(coachEntity);
       return CoachMapper.entityToDto(coachEntity);
    }

    //GETONE
    @Override
    @Transactional(readOnly = true)
    public CoachDto getCoach(long id) {
        return coachRepository.findById(id)
        .map(CoachMapper::entityToDto)
        //Exepcion a personalizar -----------------------------------------
        .orElseThrow(() -> new NoSuchElementException("Class not found with id: " + id));
    }

    //GETALL
    @Transactional(readOnly = true)
    @Override
    public Page<CoachDto> getAllCoaches(int pageNumber, int sizePage, String orderBy, String sortDir) {
       Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? Sort.Direction.DESC : Sort.Direction.ASC;
       Pageable pageable = PageRequest.of(pageNumber, sizePage, Sort.by(direction, orderBy));
       return coachRepository.findAll(pageable).map(CoachMapper::entityToDto);
    }

    //UPDATE
    @Override
    @Transactional
    public CoachDto updateChoach(long id, CoachDto coachDto) {
       CoachEntity coachEntity = coachRepository.findById(id)
        //Exepcion a personalizar -----------------------------------------
        .orElseThrow(() -> new NoSuchElementException("Class not found with id: " + id));

        coachEntity.setId(coachDto.getId());
        coachEntity.setName(coachDto.getName());
        coachEntity.setLastName(coachDto.getLastName());

        CoachEntity coachEntity2 = coachRepository.save(coachEntity);
        return CoachMapper.entityToDto(coachEntity2);
    }

    //DELETE
    @Override
    @Transactional
    public void deletCoach(long id) {
       CoachEntity coachEntity = coachRepository.findById(id)
        //Exepcion a personalizar -----------------------------------------
        .orElseThrow(() -> new NoSuchElementException("Class not found with id: " + id));
        coachRepository.delete(coachEntity);
    }

}
