package com.school.application.controller.rest;

import java.util.List;

import javax.validation.Valid;

import com.school.application.model.Persona;
import com.school.application.model.common.RepBase;
import com.school.application.repository.Persona.PersonaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping(value = "/api/v1/persona")
public class PersonaRestController {
    
    @Autowired
    private PersonaRepository repository;

    @PutMapping
    public ResponseEntity<RepBase> save(@Valid @RequestBody Persona persona){
        return ResponseEntity.ok(new RepBase(repository.save(persona)));
    }

    @PostMapping
    public ResponseEntity<RepBase> update(@RequestBody Persona persona){
        return ResponseEntity.ok(new RepBase(repository.update(persona)));
    }

    @GetMapping
    public ResponseEntity<List<Persona>> findAll(SpringDataWebProperties.Pageable pageable){
        return ResponseEntity.ok(repository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> findById(@PathVariable int id){
        return ResponseEntity.ok(repository.findId(id));
    }
}
