package com.school.application.controller.rest;

import java.util.List;

import com.school.application.model.vistaModel.TareaMateria;
import com.school.application.repository.Materia.TareaMateriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    /* RestController Vista Tarea Materia Estudiante */
/* https://lucid.app/lucidchart/11125235-04a3-4dde-b178-b0e04c149384/edit?page=7hdOQH08_l-T#?folder_id=home&browser=icon */
    
    /* RestController Vista Tarea Materia Profesor */
/* https://lucid.app/lucidchart/11125235-04a3-4dde-b178-b0e04c149384/edit?page=~XdOBMzCrmb.#?folder_id=home&browser=icon */

@RestController
@RequestMapping(value = "/api/v1/materia/tarea")
public class TareaMateriaRestController {
    
    @Autowired
    private TareaMateriaRepository repository;

    @GetMapping(value = "/{idMateria}")
    public ResponseEntity<List<TareaMateria>> findTarea(@PathVariable int idMateria){
        return ResponseEntity.ok().body(repository.findTarea(idMateria));
    }
}
