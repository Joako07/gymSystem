package com.springboot.gym.gymsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.gym.gymsystem.models.dtos.CoachDto;
import com.springboot.gym.gymsystem.services.interfaces.CoachService;
import com.springboot.gym.gymsystem.utilities.Constants;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/coach")
public class CoachController {

    @Autowired
    private CoachService coachService;

    @GetMapping("/{id}")
    public ResponseEntity<CoachDto> getCoach(@PathVariable Long id) {
        return new ResponseEntity<>(coachService.getCoach(id), HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<Page<CoachDto>> getAllCoaches(
        @RequestParam(value = "pageNum", defaultValue = Constants.NUMERO_DE_PAGINAS_POR_DEFECTO, required = false) int pageNumber,
        @RequestParam(value = "pageSize", defaultValue = Constants.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int sizePage,
        @RequestParam(value = "sortBy", defaultValue = Constants.ORDENAR_POR_DEFECTO, required = false) String orderBy,
        @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        return new ResponseEntity<>(coachService.getAllCoaches(pageNumber,sizePage,orderBy,sortDir),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CoachDto> creatCoach(@RequestBody CoachDto coachDto) {
        return new ResponseEntity<>(coachService.createCoach(coachDto),HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CoachDto> updateCoach(@PathVariable Long id, @RequestBody CoachDto coachDto) {
        return new ResponseEntity<>(coachService.updateChoach(id, coachDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletCoach(@PathVariable Long id){
        coachService.deletCoach(id);
        return new ResponseEntity<>("Coach deleted successfully", HttpStatus.OK);
    }
}
