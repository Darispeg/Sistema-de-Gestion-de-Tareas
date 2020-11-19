package com.school.application.repository.Materia;

import java.util.List;

import com.school.application.model.vistaModel.TareaMateria;

    /* Vista Tarea Materia Estudiante */
/* https://lucid.app/lucidchart/11125235-04a3-4dde-b178-b0e04c149384/edit?page=7hdOQH08_l-T#?folder_id=home&browser=icon */
    
    /* Vista Tarea Materia Profesor */
/* https://lucid.app/lucidchart/11125235-04a3-4dde-b178-b0e04c149384/edit?page=~XdOBMzCrmb.#?folder_id=home&browser=icon */

public interface TareaMateriaRep {
    public List<TareaMateria> findTarea(int materia);
}
