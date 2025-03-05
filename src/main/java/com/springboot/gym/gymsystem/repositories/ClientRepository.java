package com.springboot.gym.gymsystem.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


import com.springboot.gym.gymsystem.models.entities.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    @EntityGraph(attributePaths = {"classes"}) // Carga la relación "classes" automáticamente
   Page<ClientEntity> findAll(Pageable pageable);
}
