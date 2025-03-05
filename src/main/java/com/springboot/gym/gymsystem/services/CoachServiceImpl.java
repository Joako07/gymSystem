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
import com.springboot.gym.gymsystem.models.dtos.CoachDto;
import com.springboot.gym.gymsystem.models.entities.CoachEntity;
import com.springboot.gym.gymsystem.models.mappers.CoachMapper;
import com.springboot.gym.gymsystem.repositories.CoachRepository;
import com.springboot.gym.gymsystem.services.interfaces.CoachService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CoachServiceImpl implements CoachService {

    @Autowired
    private CoachRepository coachRepository;

    // CREATE
    @Override
    @Transactional
    public CoachDto createCoach(CoachDto coachDto) {
        CoachEntity coachEntity = CoachMapper.dtoToEntity(coachDto);
        try {
            coachRepository.save(coachEntity);
            log.info("A Coach was created");
            return CoachMapper.entityToDto(coachEntity);
        } catch (Exception e) {
            log.error("An error occurred while creating a new Coach", e);
            throw ApiErrorException.builder()
                    .error(e.getMessage())
                    .message("Ocurrió un error al crear un nuevo coach")
                    .build();
        }
    }

    // GETONE
    @Override
    @Transactional(readOnly = true)
    public CoachDto getCoach(long id) {
        return coachRepository.findById(id)
                .map(CoachMapper::entityToDto)
                .orElseThrow(() -> new NotFoundApiException("404 Not Found - Coach with ID " + id + " not found",
                        "No se encontro el coach con el Id " + id));
    }

    // GETALL
    @Transactional(readOnly = true)
    @Override
    public Page<CoachDto> getAllCoaches(int pageNumber, int sizePage, String orderBy, String sortDir) {
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(pageNumber, sizePage, Sort.by(direction, orderBy));
        return coachRepository.findAll(pageable).map(CoachMapper::entityToDto);
    }

    // UPDATE
    @Override
    @Transactional
    public CoachDto updateChoach(long id, CoachDto coachDto) {
        CoachEntity coachEntity = coachRepository.findById(id)
                .orElseThrow(() -> new NotFoundApiException("404 Not Found - Coach with ID " + id + " not found",
                        "No se encontro el coach con el Id " + id));

        coachEntity.setId(coachDto.getId());
        coachEntity.setName(coachDto.getName());
        coachEntity.setLastName(coachDto.getLastName());

        try {
            CoachEntity coachEntity2 = coachRepository.save(coachEntity);
            log.info("A Coach was updated");
            return CoachMapper.entityToDto(coachEntity2);
        } catch (Exception e) {
            log.error("An error occurred while updating a Coach", e);
            throw ApiErrorException.builder()
                    .error(e.getMessage())
                    .message("Ocurrió un error al actualizar un coach")
                    .build();
        }
    }

    // DELETE
    @Override
    @Transactional
    public void deletCoach(long id) {
        CoachEntity coachEntity = coachRepository.findById(id)
                .orElseThrow(() -> new NotFoundApiException("404 Not Found - Coach with ID " + id + " not found",
                        "No se encontro el coach con el Id " + id));
        try {
            coachRepository.delete(coachEntity);
            log.info("A Coach was deleted");
        } catch (Exception e) {
            log.error("An error occurred while deleting a Coach", e);
            throw ApiErrorException.builder()
                    .error(e.getMessage())
                    .message("Ocurrió un error al borrar un coach")
                    .build();
        }
    }

}
