package com.school.application.controller.rest;

import java.util.List;

import javax.validation.Valid;

import com.school.application.model.Estudiante;
import com.school.application.model.common.RepBase;
import com.school.application.repository.Estudiante.EstudianteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/estudiante")
public class EstudianteRestController {
    
    @Autowired
    private EstudianteRepository repository;

    @PutMapping
    public ResponseEntity<RepBase> save(@Valid @RequestBody Estudiante estudiante){
        return ResponseEntity.ok(new RepBase(repository.save(estudiante)));
    }

    @PostMapping
    public ResponseEntity<RepBase> update(@RequestBody Estudiante estudiante){
        return ResponseEntity.ok(new RepBase(repository.update(estudiante)));
    }

    @GetMapping
    public ResponseEntity<List<Estudiante>> findAll(SpringDataWebProperties.Pageable pageable){
        return ResponseEntity.ok(repository.findAll(pageable));
    }

    @GetMapping(value = "/{ci}")
    public ResponseEntity<Estudiante> findById(@PathVariable int ci){
        return ResponseEntity.ok(repository.findId(ci));
    }
}
