package com.school.application.controller.rest;

import java.util.List;

import com.school.application.model.Grado;
import com.school.application.model.common.RepBase;
import com.school.application.repository.Escuela.GradoRepository;

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
@RequestMapping("/api/v1/grado")
public class GradoRestController {
    
    @Autowired
    private GradoRepository repository;

    @PutMapping
    public ResponseEntity<RepBase> save(@RequestBody Grado grado){
        return ResponseEntity.ok(new RepBase(repository.save(grado)));
    }

    @PostMapping
    public ResponseEntity<RepBase> update(@RequestBody Grado grado){
        return ResponseEntity.ok(new RepBase(repository.update(grado)));
    }

    @GetMapping
    public ResponseEntity<List<Grado>> findAll(SpringDataWebProperties.Pageable pageable){
        return ResponseEntity.ok(repository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grado> findById(@PathVariable int id){
        return ResponseEntity.ok(repository.findId(id));
    }
}
