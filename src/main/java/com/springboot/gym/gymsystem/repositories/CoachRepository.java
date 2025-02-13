package com.springboot.gym.gymsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.gym.gymsystem.models.entities.CoachEntity;

public interface CoachRepository extends JpaRepository<CoachEntity, Long>{

}
