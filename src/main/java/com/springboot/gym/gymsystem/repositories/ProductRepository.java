package com.springboot.gym.gymsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.gym.gymsystem.models.entities.ProductEntity;

public interface ProductRepository extends JpaRepository <ProductEntity, Long>{

}
