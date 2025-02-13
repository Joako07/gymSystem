package com.springboot.gym.gymsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.gym.gymsystem.models.entities.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

}
