package com.springboot.gym.gymsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.gym.gymsystem.exceptions.ApiErrorException;
import com.springboot.gym.gymsystem.exceptions.NotFoundApiException;
import com.springboot.gym.gymsystem.models.dtos.ClassDto;
import com.springboot.gym.gymsystem.models.entities.ClassEntity;
import com.springboot.gym.gymsystem.models.mappers.ClassMapper;
import com.springboot.gym.gymsystem.repositories.ClassRepository;
import com.springboot.gym.gymsystem.services.interfaces.ClassService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClassServiceImpl implements ClassService {

   @Autowired
   private ClassRepository classRepository;

   // CREATE
   @Override
   @Transactional
   public ClassDto createClass(ClassDto classDto) {
      ClassEntity classEntity = ClassMapper.dtoToEntity(classDto);
      try {
         classRepository.save(classEntity);
         log.info("A Class was created");
         return ClassMapper.entityToDto(classEntity);
      } catch (Exception e) {
         log.error("An error occurred while creating a new Class", e);
         throw ApiErrorException.builder()
               .error(e.getMessage())
               .message("Ocurrió un error al crear la clase")
               .build();
      }
   }

   // GETALL
   @Transactional(readOnly = true)
   @Override
   public Page<ClassDto> getAllClass(int pageNumber, int sizePage, String orderBy, String sortDir) {
      Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? Sort.Direction.DESC : Sort.Direction.ASC;
      Pageable pageable = PageRequest.of(pageNumber, sizePage, Sort.by(direction, orderBy));
      return classRepository.findAll(pageable).map(ClassMapper::entityToDto);
   }

   // GETONE
   @Override
   @Transactional(readOnly = true)
   public ClassDto getById(long id) {
      return classRepository.findById(id)
            .map(ClassMapper::entityToDto)
            .orElseThrow(() -> new NotFoundApiException(
                  "404 Not Found - Class with ID " + id + " not found", "No se encontro la clase con el Id " + id));
   }

   // UPDATE
   @Override
   @Transactional
   public ClassDto updateClass(ClassDto classDto, long id) {
      ClassEntity classEntity = classRepository.findById(id)
            .orElseThrow(() -> new NotFoundApiException(
                  "404 Not Found - Class with ID " + id + " not found", "No se encontro la clase con el Id " + id));

      classEntity.setId(id);
      classEntity.setActivity(classDto.getActivity());
      classEntity.setDays(classDto.getDays());
      classEntity.setStartClass(classDto.getStartClass());
      classEntity.setEndTime(classDto.getEndTime());

      try {
         ClassEntity classEntity2 = classRepository.save(classEntity);
         log.info("A Class was updated");
         return ClassMapper.entityToDto(classEntity2);
      } catch (Exception e) {
         log.error("An error occurred while updating a Class", e);
         throw ApiErrorException.builder()
               .error(e.getMessage())
               .message("Ocurrió un error al actualizar una clase")
               .build();
      }
   }

   // DELETE
   @Override
   @Transactional
   public void deletClass(long id) {
      ClassEntity classEntity = classRepository.findById(id)
            .orElseThrow(() -> new NotFoundApiException(
                  "404 Not Found - Class with ID " + id + " not found", "No se encontro la clase con el Id " + id));
      try {
         classRepository.delete(classEntity);
         log.info("A Class was deleted");
      } catch (Exception e) {
         log.error("An error occurred while deleting a Class", e);
         throw ApiErrorException.builder()
               .error(e.getMessage())
               .message("Ocurrió un error al borrar una clase")
               .build();
      }
   }
}
