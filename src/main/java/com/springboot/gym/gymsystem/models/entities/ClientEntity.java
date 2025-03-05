package com.springboot.gym.gymsystem.models.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.springboot.gym.gymsystem.models.enums.MembershipTypeEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 

    @Column(nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String cellphone;

    @Column(nullable = false)
    private MembershipTypeEnum type;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
                name="client_class",
                joinColumns = @JoinColumn(name = "client_id"),
                inverseJoinColumns = @JoinColumn(name = "class_id")                
                )
    @JsonManagedReference
    private Set<ClassEntity> classes = new HashSet<>();

}
