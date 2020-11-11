package com.school.application.repository.Materia;

import java.util.List;

import com.school.application.model.ProfesorMateria;
import com.school.application.repository.RepositoryCommon;

public interface ProfMateriaRep extends RepositoryCommon<ProfesorMateria>{
    public List<ProfesorMateria> findMateriasProfesor(int idProfesor);
    public boolean delete(int idProfesor, int idMateria);
}
