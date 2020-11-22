package com.school.application.controller.rest;

import java.util.List;

import javax.validation.Valid;

import com.school.application.model.vistaModel.ProfesorMateria;
import com.school.application.model.common.RepBase;
import com.school.application.repository.Materia.ProfMateriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

                    /*RestController VISTA Materia-Estudiante */
/* https://lucid.app/lucidchart/11125235-04a3-4dde-b178-b0e04c149384/edit?page=EedOYkK~5wn-#?folder_id=home&browser=icon */


@RestController
@RequestMapping("/api/v1/materia/profesor")
public class ProfMateriaRestController {
    
    @Autowired
    private ProfMateriaRepository repository;

    /* Para Insertar Datos a la Tabla : profesormateria */
    @PutMapping
    public ResponseEntity<RepBase> save(@Valid @RequestBody ProfesorMateria profesorMat){
        return ResponseEntity.ok(new RepBase(repository.save(profesorMat)));
    }   

    /* Para cambiar el estado de un profesor registrado con una materia */
    @PostMapping
    public ResponseEntity<RepBase> estado(@RequestBody ProfesorMateria profesorMat){
        return ResponseEntity.ok(new RepBase(repository.update(profesorMat)));
    }

    /* Busca todas las materias que imparte un Profesor
    con el ci del profesor en la URL*/
    @GetMapping("/{ci}")
    public ResponseEntity<List<ProfesorMateria>> findById(@PathVariable int ci){
        return ResponseEntity.ok(repository.findMateriasProfesor(ci));
    }

    @DeleteMapping(value = "/delete/{idProfesor}/{idMateria}")
    public ResponseEntity<RepBase> delete(@PathVariable int idProfesor, @PathVariable int idMateria){
        return ResponseEntity.ok(new RepBase(repository.delete(idProfesor, idMateria)));
    }
}