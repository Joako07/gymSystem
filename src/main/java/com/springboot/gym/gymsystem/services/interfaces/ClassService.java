package com.springboot.gym.gymsystem.services.interfaces;

import org.springframework.data.domain.Page;

import com.springboot.gym.gymsystem.models.dtos.ClassDto;

public interface ClassService {

    public ClassDto createClass (ClassDto classDto);

    public Page<ClassDto> getAllClass(int pageNumber, int sizePage, String orderBy, String sortDir);   
    
    public ClassDto getById(long id);

    public ClassDto updateClass(ClassDto classDto, long id);

    public void deletClass(long id);

}
