package com.springboot.gym.gymsystem.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.gym.gymsystem.models.dtos.ClassDto;
import com.springboot.gym.gymsystem.models.entities.ClassEntity;
import com.springboot.gym.gymsystem.models.mappers.ClassMapper;
import com.springboot.gym.gymsystem.repositories.ClassRepository;
import com.springboot.gym.gymsystem.services.interfaces.ClassService;

@Service
public class ClassServiceImpl implements ClassService{

    @Autowired
    private ClassRepository classRepository;

    //CREATE
    @Override
    @Transactional
    public ClassDto createClass(ClassDto classDto) {
       ClassEntity classEntity = ClassMapper.dtoToEntity(classDto);
       classRepository.save(classEntity);
       return ClassMapper.entityToDto(classEntity);
    }

    //GETALL
    @Transactional(readOnly = true)
    @Override
    public Page<ClassDto> getAllClass(int pageNumber, int sizePage, String orderBy, String sortDir) {
       Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? Sort.Direction.DESC : Sort.Direction.ASC;
       Pageable pageable = PageRequest.of(pageNumber, sizePage, Sort.by(direction, orderBy));
       return classRepository.findAll(pageable).map(ClassMapper :: entityToDto);
    }

    //GETONE
    @Override
    @Transactional(readOnly = true)
    public ClassDto getById(long id) {
     return classRepository.findById(id)
     .map(ClassMapper::entityToDto)
     //Exepcion a personalizar -----------------------------------------
     .orElseThrow(() -> new NoSuchElementException("Class not found with id: " + id));
    }

    //UPDATE
    @Override
    @Transactional
    public ClassDto updateClass(ClassDto classDto, long id) {
       ClassEntity classEntity = classRepository.findById(id)
       //Exepcion a personalizar -----------------------------------------
       .orElseThrow(() -> new NoSuchElementException("Class not found with id: " + id));

        classEntity.setId(id);
        classEntity.setActivity(classDto.getActivity());
        classEntity.setDays(classDto.getDays());
        classEntity.setStartClass(classDto.getStartClass());
        classEntity.setEndTime(classDto.getEndTime());

        ClassEntity classEntity2 = classRepository.save(classEntity);
        return ClassMapper.entityToDto(classEntity2);
    }

    //DELETE
    @Override
    @Transactional
    public void deletClass(long id) {
      ClassEntity classEntity = classRepository.findById(id)
      //Exepcion a personalizar -----------------------------------------
      .orElseThrow(() -> new NoSuchElementException("Class not found with id: " + id));

      classRepository.delete(classEntity);
    }

}
