package com.school.application.controller.rest;

import java.util.List;

import com.school.application.model.EstMateria;
import com.school.application.model.common.RepBase;
import com.school.application.repository.Materia.EstMateriaRepository;

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
@RequestMapping("/api/v1/materia/estudiante")
public class EstMateriaRestController {
    
    @Autowired
    private EstMateriaRepository repository;

    @PutMapping
    public ResponseEntity<RepBase> save(@RequestBody EstMateria eMateria){
        return ResponseEntity.ok(new RepBase(repository.save(eMateria)));
    }

    @PostMapping
    public ResponseEntity<RepBase> update(@RequestBody EstMateria eMateria){
        return ResponseEntity.ok(new RepBase(repository.update(eMateria)));
    }

    @GetMapping
    public ResponseEntity<List<EstMateria>> findAll(SpringDataWebProperties.Pageable pageable){
        return ResponseEntity.ok(repository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstMateria> findById(@PathVariable int id){
        return ResponseEntity.ok(repository.findId(id));
    }
}
