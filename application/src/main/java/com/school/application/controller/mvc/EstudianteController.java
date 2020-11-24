package com.school.application.controller.mvc;

import java.util.List;

import com.school.application.model.Estudiante;
import com.school.application.model.RespuestaTarea;
import com.school.application.model.vistaModel.DetalleTarea;
import com.school.application.model.vistaModel.EstMateria;
import com.school.application.model.vistaModel.TareaMateria;
import com.school.application.repository.Estudiante.EstudianteRepository;
import com.school.application.repository.Materia.EstMateriaRepository;
import com.school.application.repository.Materia.TareaMateriaRepository;
import com.school.application.repository.Tarea.DetalleTareaRepository;
import com.school.application.repository.Tarea.respuestas.RespuestaRepository;
import com.school.application.repository.Tarea.service.FileRespuestaRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/views/estudiantes")
public class EstudianteController {

    private Estudiante usuario = null;
    private DetalleTarea tDetalle = null;
    Log log = LogFactory.getLog(getClass());
    
    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private EstMateriaRepository eMateriaRepository;

    @Autowired
    private TareaMateriaRepository tMateriaRepository;

    @Autowired
    private DetalleTareaRepository dTareaRepository;

    @Autowired
    private FileRespuestaRepository fileRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    public void buscarUsuario(int ci){
        usuario = estudianteRepository.findId(ci);
    }

    @GetMapping(value = "/materias/{ci}")
    public String listarMaterias(@PathVariable("ci") int ci, Model model) {
        List<EstMateria> listaMaterias = eMateriaRepository.findEstudiante(ci);
        buscarUsuario(ci);
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
        tDetalle = dTareaRepository.findTarea(tarea);
        List<RespuestaTarea> rTarea = respuestaRepository.findRespuestaEstudiante((int)tDetalle.getIdTarea(), (int)usuario.getIdEstudiante());
        model.addAttribute("tareaDetalle", tDetalle);
        model.addAttribute("respuesta", rTarea);
        return "/views/estudiantes/estudiante_detalle_tarea";
    }

    @PostMapping(value = "/tareas/detalles/upload")
    public String subirTarea(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()) {
            return "/views/estudiantes/estudiante_detalle_tarea";
        }
        try {
            fileRepository.save((int)tDetalle.getIdTarea(),(int)usuario.getIdEstudiante(), file);
            return "redirect:/views/estudiantes//materias/tareas/detalles/" + tDetalle.getIdTarea();
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al Subir los Archivos");
        }
    }
}