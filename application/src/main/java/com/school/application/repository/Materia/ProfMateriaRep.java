package com.school.application.repository.Materia;

import java.util.List;

import com.school.application.model.ProfesorMateria;

                /* VISTA Materia-Profesor */
/* https://lucid.app/lucidchart/11125235-04a3-4dde-b178-b0e04c149384/edit?page=EedOYkK~5wn-#?folder_id=home&browser=icon */

public interface ProfMateriaRep {

    public boolean save(ProfesorMateria profMat);
    public boolean update(ProfesorMateria profMat);
    public List<ProfesorMateria> findMateriasProfesor(int ci);
    public boolean delete(int idProfesor, int idMateria);
}
