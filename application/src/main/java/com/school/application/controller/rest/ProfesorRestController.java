package com.school.application.controller.rest;

import java.util.List;

import com.school.application.model.Profesor;
import com.school.application.model.common.RepBase;
import com.school.application.repository.Profesor.ProfesorRepository;

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
@RequestMapping("/api/v1/profesor")
public class ProfesorRestController {
    
    @Autowired
    private ProfesorRepository repository;

    @PutMapping
    public ResponseEntity<RepBase> save(@RequestBody Profesor profesor){
        return ResponseEntity.ok(new RepBase(repository.save(profesor)));
    }

    @PostMapping
    public ResponseEntity<RepBase> update(@RequestBody Profesor profesor){
        return ResponseEntity.ok(new RepBase(repository.update(profesor)));
    }

    @GetMapping
    public ResponseEntity<List<Profesor>> findAll(SpringDataWebProperties.Pageable pageable){
        return ResponseEntity.ok(repository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> findById(@PathVariable int id){
        return ResponseEntity.ok(repository.findId(id));
    }
}
