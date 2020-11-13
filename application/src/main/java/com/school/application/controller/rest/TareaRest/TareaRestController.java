package com.school.application.controller.rest.TareaRest;

import java.util.List;

import javax.validation.Valid;

import com.school.application.model.Tarea;
import com.school.application.model.common.RepBase;
import com.school.application.repository.Tarea.TareaRepository;

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
@RequestMapping(value = "/api/v1/tarea")
public class TareaRestController {
    
    @Autowired
    private TareaRepository repository;

    @PutMapping
    public ResponseEntity<RepBase> save(@Valid @RequestBody Tarea tarea){
        return ResponseEntity.ok(new RepBase(repository.save(tarea)));
    }

    @PostMapping
    public ResponseEntity<RepBase> update(@RequestBody Tarea tarea){
        return ResponseEntity.ok(new RepBase(repository.update(tarea)));
    }

    @GetMapping
    public ResponseEntity<List<Tarea>> findAll(SpringDataWebProperties.Pageable pageable){
        return ResponseEntity.ok(repository.findAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Tarea> findById(@PathVariable int id){
        return ResponseEntity.ok(repository.findId(id));
    }
}
