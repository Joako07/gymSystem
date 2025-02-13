package com.springboot.gym.gymsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.gym.gymsystem.models.entities.ClassEntity;

public interface ClassRepository extends JpaRepository <ClassEntity, Long> {

}
