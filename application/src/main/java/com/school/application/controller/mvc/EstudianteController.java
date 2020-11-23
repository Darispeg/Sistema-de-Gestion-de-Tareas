package com.school.application.controller.mvc;

import java.util.List;

import com.school.application.model.vistaModel.DetalleTarea;
import com.school.application.model.vistaModel.EstMateria;
import com.school.application.model.vistaModel.TareaMateria;
import com.school.application.repository.Materia.EstMateriaRepository;
import com.school.application.repository.Materia.TareaMateriaRepository;
import com.school.application.repository.Tarea.DetalleTareaRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/views/estudiantes")
public class EstudianteController {

    @Autowired
    private EstMateriaRepository eMateriaRepository;

    @Autowired
    private TareaMateriaRepository tMateriaRepository;

    @Autowired
    private DetalleTareaRepository dTareaRepository;

    Log log = LogFactory.getLog(getClass());

    @GetMapping(value = "/materias/{ci}")
    public String listarMaterias(@PathVariable("ci") int ci, Model model) {
        List<EstMateria> listaMaterias = eMateriaRepository.findEstudiante(ci);

        model.addAttribute("materias", listaMaterias);
        return "/views/estudiantes/estudiante_materias";
    }

    @GetMapping(value = "/materias/tareas/{materia}")
    public String listarTareas(@PathVariable("materia") int materia, Model model) {
        List<TareaMateria> listaTareas = tMateriaRepository.findTarea(materia);
        model.addAttribute("tareas", listaTareas);
        return "/views/estudiantes/estudiante_tareas";
    }

    @GetMapping(value = "/materias/tareas/detalles/{tarea}")
    public String verDetalles(@PathVariable("tarea") int tarea, Model model) {
        DetalleTarea tDetalle = dTareaRepository.findTarea(tarea);
        model.addAttribute("tareaDetalle", tDetalle);
        return "/views/estudiantes/estudiante_detalle_tarea";
    }
}