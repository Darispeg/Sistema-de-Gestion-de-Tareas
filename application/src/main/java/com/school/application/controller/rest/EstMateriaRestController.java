package com.school.application.controller.rest;

import java.util.List;

import javax.validation.Valid;

import com.school.application.model.EstMateria;
import com.school.application.model.common.RepBase;
import com.school.application.repository.Materia.EstMateriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


                    /*RestController VISTA Materia-Estudiante */
/* https://lucid.app/lucidchart/11125235-04a3-4dde-b178-b0e04c149384/edit?page=QIcOlET8ieVq#?folder_id=home&browser=icon */

@RestController
@RequestMapping("/api/v1/materia/estudiante")
public class EstMateriaRestController {
    
    @Autowired
    private EstMateriaRepository repository;

    /* Para Insertar Datos a la Tabla : estmateria */
    @PutMapping
    public ResponseEntity<RepBase> save(@Valid @RequestBody EstMateria eMateria){
        return ResponseEntity.ok(new RepBase(repository.save(eMateria)));
    }

    /* Para cambiar el estado de un estudiante registrado con una materia */
    @PostMapping
    public ResponseEntity<RepBase> update(@RequestBody EstMateria eMateria){
        return ResponseEntity.ok(new RepBase(repository.update(eMateria)));
    }

    /* Busca todas las materias en las que esta registrado un estudiante
        con el ci del estudiante en la URL*/
    @GetMapping("/{ci}")
    public ResponseEntity<List<EstMateria>> findById(@PathVariable int ci){
        return ResponseEntity.ok(repository.findEstudiante(ci));
    }
}