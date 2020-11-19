package com.school.application.controller.rest.TareaRest;

import java.util.List;

import com.school.application.model.RespuestaTarea;
import com.school.application.repository.Tarea.respuestas.RespuestaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/respuesta")
public class RespuestaRestController {
    
    @Autowired
    private RespuestaRepository repository;

    @GetMapping
    public ResponseEntity<List<RespuestaTarea>> findAll(SpringDataWebProperties.Pageable pageable){
        return ResponseEntity.ok().body(repository.findAll(pageable));
    }
}
