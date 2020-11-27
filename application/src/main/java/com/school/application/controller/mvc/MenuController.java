package com.school.application.controller.mvc;

import java.util.List;

import com.school.application.model.TareaDetalles;
import com.school.application.repository.Tarea.TareaDetallesRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MenuController {

    Log log = LogFactory.getLog(getClass());
    
    @Autowired
    private TareaDetallesRepository tDetallesRepository;
    
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

    @GetMapping(value = "/tareas/archivos/{idTarea}")
    public String mostrarTareaArchivos(@PathVariable("idTarea") int idTarea, Model model){
        List<TareaDetalles> detalles = tDetallesRepository.findDetalles(idTarea);
        model.addAttribute("detalles", detalles);
        return "/views/profesores/tarea_documentos";
    }
}
