package com.school.application.controller.rest;

import java.util.List;

import javax.validation.Valid;

import com.school.application.model.Materia;
import com.school.application.model.common.RepBase;
import com.school.application.repository.Materia.MateriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/api/v1/materia")
public class MateriaRestController {
    
    @Autowired
    private MateriaRepository repository;

    @PutMapping()
    public ResponseEntity<RepBase> save(@Valid @RequestBody Materia materia) {
        return ResponseEntity.ok(new RepBase(repository.save(materia)));
    }

    @PostMapping
    public ResponseEntity<RepBase> update(@RequestBody Materia materia){
        return ResponseEntity.ok(new RepBase(repository.update(materia)));
    }

    @GetMapping
    public ResponseEntity<List<Materia>> findAll(SpringDataWebProperties.Pageable pageable){
        return ResponseEntity.ok(repository.findAll(pageable)); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materia> findById(@PathVariable int id){
        return ResponseEntity.ok(repository.findId(id));
    }
}
