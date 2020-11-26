package com.school.application.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {
    
    @GetMapping(value = "/menu")
    public String mostrarMenu(){
        return "/views/plantilla/home";
    } 

    @GetMapping(value = "/materias")
    public String mostrarMaterias(){
        return "/views/profesores/profesor_materias";
    }

    @GetMapping(value = "/tareas")
    public String mostrarTareas(){
        return "/views/profesores/tarea_profesor";
    }

    @GetMapping(value = "/tareas/detalles")
    public String mostrarTareasDetalles(){
        return "/views/profesores/tarea_detalle_profesor";
    }

    @GetMapping(value = "/tareas/respuesta")
    public String mostrarTareasRespuesta(){
        return "/views/profesores/tarea_respuesta";
    }
}
