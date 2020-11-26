package com.school.application.repository.Estudiante;

import com.school.application.model.Estudiante;
import com.school.application.repository.RepositoryCommon;

public interface EstudianteRep extends RepositoryCommon<Estudiante> {
    public Estudiante login(String usuario, String contrasena);
}
