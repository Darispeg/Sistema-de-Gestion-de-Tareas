package com.school.application.controller.mvc;

import java.util.List;

import com.school.application.model.Curso;
import com.school.application.model.Materia;
import com.school.application.model.Profesor;
import com.school.application.model.RespuestaTarea;
import com.school.application.model.Tarea;
import com.school.application.model.vistaModel.DetalleTarea;
import com.school.application.model.vistaModel.ProfesorMateria;
import com.school.application.model.vistaModel.TareaMateria;
import com.school.application.repository.Curso.CursoRepository;
import com.school.application.repository.Materia.MateriaRepository;
import com.school.application.repository.Materia.ProfMateriaRepository;
import com.school.application.repository.Materia.TareaMateriaRepository;
import com.school.application.repository.Profesor.ProfesorRepository;
import com.school.application.repository.Tarea.DetalleTareaRepository;
import com.school.application.repository.Tarea.TareaRepository;
import com.school.application.repository.Tarea.respuestas.RespuestaRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/views")
public class ProfesorController {

    private Profesor usuario;
    private DetalleTarea tDetalle = null;

    @Autowired
    private ProfMateriaRepository pMateriaRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private DetalleTareaRepository dTareaRepository;

    @Autowired
    private TareaMateriaRepository tMateriaRepository;

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    Log log = LogFactory.getLog(getClass());

/* -------------                       LOGIN                           ---------------------------*/

    @GetMapping(value = "/login")
    public String mostrarMaterias(Model model){
        Profesor profesor = new Profesor();
        model.addAttribute("usuario", profesor);
        return "/views/profesores/login_profesor";
    }

    @PostMapping(value = "/login/acess")
    public String login(@ModelAttribute Profesor usser ,RedirectAttributes attributes){
        usuario = profesorRepository.loginProfesor(usser.getCorreo(), usser.getContrasena());
        attributes.addFlashAttribute("success", "Bienvenido " + usuario.getCorreo());
        return "redirect:/views/materias/" + usuario.getCi();
    }

/* ------------------------------------------------------------------------------------------------ */

    @GetMapping(value = "/materias/{ci}")
    public String listaMateriaS(@PathVariable("ci") int ci, Model model, RedirectAttributes attributes){
        List<ProfesorMateria> listaMaterias = null;
        if(ci > 0){
            listaMaterias = pMateriaRepository.findMateriasProfesor(ci);
            if(listaMaterias.isEmpty()){
                attributes.addFlashAttribute("error", "ATENCION: el cliente requerido no existe");
                return "redirect:/views/profesores/profesor_materias";
            }
        }else{
            attributes.addFlashAttribute("error", "ATENCION: error con el ID del Cliente");
            return "redirect:/views/profesores/profesor_materias";
        }
        model.addAttribute("materias", listaMaterias);
        return "/views/profesores/profesor_materias";
    }

/* -----------------------------       Tareas      ----------------------------------------------- */

    @GetMapping(value = "/materia/tarea/{materia}")
    public String listarTarea(@PathVariable("materia") int materia, Model model){
        
        List<TareaMateria> listaTareas = tMateriaRepository.findTarea(materia);



        model.addAttribute("tareas", listaTareas);

        Tarea nuevo = new Tarea();
        Materia findMateria = materiaRepository.findId(materia);
        Curso findCurso = cursoRepository.findId((int)findMateria.getIdCurso());
        
        nuevo.setIdCurso(findCurso.getIdCurso());
        nuevo.setIdMateria(findMateria.getIdMateria());

        model.addAttribute("findCurso", findCurso);
        model.addAttribute("findMateria", findMateria);

        model.addAttribute("nuevo", nuevo);
        
        return "/views/profesores/tarea_profesor";
    }

    @GetMapping(value = "/materias/tareas/detalles/{tarea}")
    public String verDetalles(@PathVariable("tarea") int tarea, Model model) {
        tDetalle = dTareaRepository.findTarea(tarea);
        List<RespuestaTarea> listaRespuestas = respuestaRepository.findAllRespuestas(tarea);
        model.addAttribute("tareaDetalle", tDetalle);
        model.addAttribute("respuestas", listaRespuestas);
        return "/views/profesores/tarea_detalle_profesor";
    }

    @PostMapping(value = "/materia/tarea/save")
    public String asignarTarea(@ModelAttribute Tarea tarea){
        tareaRepository.save(tarea);
        return "redirect:/views/materia/tarea/" + tarea.getIdMateria();
    }

/* ------------------------------------------------------------------------------------------------ */

/* -----------------------------       Respuesta Tarea      ----------------------------------------------- */
    @GetMapping(value = "/materias/tareas/respuesta/{estudiante}")
    public String respuestaEstudiante(@PathVariable("estudiante") int estudiante, Model model) {
        List<RespuestaTarea> rTarea = respuestaRepository.findRespuestaEstudiante((int)tDetalle.getIdTarea(), estudiante);
        model.addAttribute("respuesta", rTarea.get(0));
        return "/views/profesores/tarea_respuesta";
    }

    @PostMapping(value = "/respuesta/calificar")
    public String calificarTarea(@ModelAttribute RespuestaTarea respuesta){
        respuestaRepository.calificarTarea(respuesta);
        return "redirect:/views/materias/tareas/detalles/" + respuesta.getIdTarea();
    }

}
