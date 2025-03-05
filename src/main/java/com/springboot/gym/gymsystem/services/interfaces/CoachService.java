package com.springboot.gym.gymsystem.services.interfaces;

import org.springframework.data.domain.Page;

import com.springboot.gym.gymsystem.models.dtos.CoachDto;

public interface CoachService {

    public CoachDto createCoach(CoachDto coachDto);

    public CoachDto getCoach (long id);

    public Page<CoachDto> getAllCoaches(int pageNumber, int sizePage, String orderBy, String sortDir);

    public CoachDto updateChoach (long id, CoachDto coachDto);

    public void deletCoach(long id);

}
