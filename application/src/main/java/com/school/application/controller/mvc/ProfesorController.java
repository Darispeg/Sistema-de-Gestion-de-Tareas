package com.school.application.controller.mvc;

import java.util.List;

import javax.validation.Valid;

import com.school.application.model.Curso;
import com.school.application.model.Materia;
import com.school.application.model.Profesor;
import com.school.application.model.RespuestaTarea;
import com.school.application.model.Tarea;
import com.school.application.model.vistaModel.ProfesorMateria;
import com.school.application.model.vistaModel.TareaMateria;
import com.school.application.repository.Curso.CursoRepository;
import com.school.application.repository.Materia.MateriaRepository;
import com.school.application.repository.Materia.ProfMateriaRepository;
import com.school.application.repository.Materia.TareaMateriaRepository;
import com.school.application.repository.Profesor.ProfesorRepository;
import com.school.application.repository.Tarea.TareaRepository;
import com.school.application.repository.Tarea.respuestas.RespuestaRepository;
import com.school.application.repository.Tarea.service.FileServiceRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/views")
public class ProfesorController {

    private Profesor usuario;
    private Tarea tDetalle = null;
    private int idTarea = 0;

    @Autowired
    private ProfMateriaRepository pMateriaRepository;

    @Autowired
    private ProfesorRepository profesorRepository;


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

    @Autowired
    private FileServiceRepository fileServiceRepository;

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

/* -----------------       MENU      ----------------------------------- */

    @GetMapping(value = "/home")
    public String home(Model model){
        model.addAttribute("profesor", usuario);
        return "/views/profesores/home";
    }

/* ---------------------------------------------------------------------- */

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
        idTarea = tarea;
        tDetalle = tareaRepository.findId(tarea);

        model.addAttribute("tareaDetalle", tDetalle);

        List<RespuestaTarea> listaRespuestas = respuestaRepository.findAllRespuestas(tarea);
        model.addAttribute("tareaDetalle", tDetalle);
        model.addAttribute("respuestas", listaRespuestas);
        return "/views/profesores/tarea_detalle_profesor";
    }

    @PostMapping(value = "/materia/tarea/save")
    public String asignarTarea(@ModelAttribute Tarea tarea, RedirectAttributes attributes){
        tareaRepository.save(tarea);
        attributes.addFlashAttribute("success", "Tarea programada la tarea con exito");
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
    public String calificarTarea(@Valid @ModelAttribute RespuestaTarea respuesta, BindingResult result, RedirectAttributes attributes, Model model){

        if(result.hasErrors()){
            log.info("\n\n" + "Se encontraron errores en el formulario");
            model.addAttribute("respuesta", respuesta);

            return "/views/profesores/tarea_respuesta";
        }

        respuestaRepository.calificarTarea(respuesta);
        attributes.addFlashAttribute("info", "Tarea calificada con exito");
        return "redirect:/views/materias/tareas/detalles/" + respuesta.getIdTarea();
    }

/* --------------------------      Subir Archivos ------------------------- */
    @PostMapping(value = "/tareas/detalles/upload")
    public String subirTarea(@RequestParam("file") MultipartFile file, RedirectAttributes attributes){
        log.info("\n\n" + tDetalle.getIdMateria() + "\n\n");
        if (file.isEmpty()) {
            attributes.addFlashAttribute("warning", "Por favor seleccione un Archivo");
            return "/views/estudiantes/tarea_detalle_profesor";
        }
        try {
            fileServiceRepository.save(idTarea, file);
            attributes.addFlashAttribute("success", "Archivo subido con exito");
            return "redirect:/views/materias/tareas/detalles/" + idTarea;
        } catch (RuntimeException e) {
            log.info("\n\n" + tDetalle.getIdMateria() + "\n\n");
            attributes.addFlashAttribute("danger", "Error al subir archivo");
            throw new RuntimeException("Error al Subir los Archivos");
        }
    }
}
