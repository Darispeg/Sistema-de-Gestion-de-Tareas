package com.school.application.controller.rest;

import java.util.List;

import javax.validation.Valid;

import com.school.application.model.Curso;
import com.school.application.model.common.RepBase;
import com.school.application.repository.Curso.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping(value = "/api/v1/curso")
public class CursoRestController {
    
    @Autowired
    private CursoRepository repository;

    @PutMapping
    public ResponseEntity<RepBase> save(@Valid @RequestBody Curso curso) {
        return ResponseEntity.ok(new RepBase(repository.save(curso)));
    }

    @PostMapping
    public ResponseEntity<RepBase> update(@RequestBody Curso curso){
        return ResponseEntity.ok(new RepBase(repository.update(curso)));
    }

    @GetMapping
    public ResponseEntity<List<Curso>> findAll(SpringDataWebProperties.Pageable pageable){
        return ResponseEntity.ok(repository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable int id){
        return ResponseEntity.ok(repository.findId(id));
    }

    @GetMapping("/paralelo/{par}")
    public ResponseEntity<List<Curso>> buscarParalelo(@PathVariable String par){
        return ResponseEntity.ok(repository.buscarParalelo(par));
    }
}
