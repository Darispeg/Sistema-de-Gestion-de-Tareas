package com.school.application.controller.mvc;

import java.util.List;

import com.school.application.model.vistaModel.EstMateria;
import com.school.application.repository.Materia.EstMateriaRepository;

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
public class MateriaController {
    
    @Autowired
    private EstMateriaRepository eMateriaRepository;

    Log log = LogFactory.getLog(getClass());

    @GetMapping(value = "/materias/{ci}")
    public String listarMaterias(@PathVariable("ci") int ci, Model model){
        List<EstMateria> listaMaterias = eMateriaRepository.findEstudiante(ci);

        model.addAttribute("materias", listaMaterias);
        return "/views/estudiantes/estudiante_materias";
    }
}
