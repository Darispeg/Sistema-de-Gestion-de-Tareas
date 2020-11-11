package com.school.application.controller.rest;

import java.util.List;

import com.school.application.model.Escuela;
import com.school.application.model.common.RepBase;
import com.school.application.repository.Escuela.EscuelaRepository;

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
@RequestMapping(value = "/api/v1/escuela")
public class EscuelaRestController {
    
    @Autowired
    private EscuelaRepository repository;

    @PutMapping
    public ResponseEntity<RepBase> save(@RequestBody Escuela escuela){
        return ResponseEntity.ok(new RepBase(repository.save(escuela)));
    }

    @PostMapping
    public ResponseEntity<RepBase> update(@RequestBody Escuela escuela){
        return ResponseEntity.ok(new RepBase(repository.update(escuela)));
    }

    @GetMapping
    public ResponseEntity<List<Escuela>> findAll(SpringDataWebProperties.Pageable pageable){
        return ResponseEntity.ok(repository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Escuela> findById(@PathVariable int id){
        return ResponseEntity.ok(repository.findId(id));
    }
}
