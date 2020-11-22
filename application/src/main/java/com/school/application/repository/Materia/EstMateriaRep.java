package com.school.application.repository.Materia;

import java.util.List;

import com.school.application.model.vistaModel.EstMateria;


                /* VISTA Materia-Estudiante */
/* https://lucid.app/lucidchart/11125235-04a3-4dde-b178-b0e04c149384/edit?page=QIcOlET8ieVq#?folder_id=home&browser=icon */

public interface EstMateriaRep {

    public boolean save(EstMateria eMateria);
    public boolean update(EstMateria eMateria);
    public List<EstMateria> findEstudiante(int id);
}
