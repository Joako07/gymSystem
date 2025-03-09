package com.springboot.gym.gymsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.gym.gymsystem.models.dtos.ClassDto;
import com.springboot.gym.gymsystem.services.interfaces.ClassService;
import com.springboot.gym.gymsystem.utilities.Constants;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping("/{id}")
    public ResponseEntity<ClassDto> getClassById(@PathVariable Long id) {
        return new ResponseEntity<>(classService.getById(id), HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<Page<ClassDto>> getAllClasses(
        @RequestParam(value = "pageNum", defaultValue = Constants.NUMERO_DE_PAGINAS_POR_DEFECTO, required = false)int pageNumber,
        @RequestParam(value = "pageSize", defaultValue = Constants.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false)int sizePage,
        @RequestParam(value = "sortBy", defaultValue = Constants.ORDENAR_POR_DEFECTO, required = false)String orderBy,
        @RequestParam(value = "sortDir", defaultValue = "asc",required = false)String sortDir){
        return new ResponseEntity<>(classService.getAllClass(pageNumber,sizePage,orderBy,sortDir), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<ClassDto> creatClass(@Valid @RequestBody ClassDto classDto) {
        return new ResponseEntity<>(classService.createClass(classDto), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ClassDto> updateClass(@PathVariable Long id,@Valid @RequestBody ClassDto classDto) {
        return new ResponseEntity<>(classService.updateClass(classDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletClass(@PathVariable Long id){
        classService.deletClass(id);
        return new ResponseEntity<>("Class deleted successfully",HttpStatus.OK);
    }
}
