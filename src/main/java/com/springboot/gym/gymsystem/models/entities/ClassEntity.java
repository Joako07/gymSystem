package com.springboot.gym.gymsystem.models.entities;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.springboot.gym.gymsystem.models.enums.DaysEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 

    @Column(nullable = false)
    private String activity;

    @Column(nullable = false)
    private Set<DaysEnum> days;

    @Column(nullable = false)
    private LocalTime startClass;

    @Column(nullable = false)
    private LocalTime endTime;

    @ManyToMany(mappedBy = "classes", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<ClientEntity> clients = new HashSet<>();

}
