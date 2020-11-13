package com.school.application.controller.rest;

import java.util.List;

import javax.validation.Valid;

import com.school.application.model.ProfesorMateria;
import com.school.application.model.common.RepBase;
import com.school.application.repository.Materia.ProfMateriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/v1/materiaprofesor")
public class ProfMateriaRestController {
    
    @Autowired
    private ProfMateriaRepository repository;

    @PutMapping
    public ResponseEntity<RepBase> save(@Valid @RequestBody ProfesorMateria profesorMat){
        return ResponseEntity.ok(new RepBase(repository.save(profesorMat)));
    }   

    @PostMapping
    public ResponseEntity<RepBase> estado(@RequestBody ProfesorMateria profesorMat){
        return ResponseEntity.ok(new RepBase(repository.update(profesorMat)));
    }

    @GetMapping
    public ResponseEntity<List<ProfesorMateria>> findAll(SpringDataWebProperties.Pageable pageable){
        return ResponseEntity.ok(repository.findAll(pageable));
    }  

    @GetMapping("/{id}")
    public ResponseEntity<List<ProfesorMateria>> findById(@PathVariable int id){
        return ResponseEntity.ok(repository.findMateriasProfesor(id));
    }

    @DeleteMapping(value = "/delete/{idProfesor}/{idMateria}")
    public ResponseEntity<RepBase> delete(@PathVariable int idProfesor, @PathVariable int idMateria){
        return ResponseEntity.ok(new RepBase(repository.delete(idProfesor, idMateria)));
    }
}
