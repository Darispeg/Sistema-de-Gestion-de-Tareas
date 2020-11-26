package com.school.application.repository.Profesor;

import com.school.application.model.Profesor;
import com.school.application.repository.RepositoryCommon;

public interface ProfesorRep extends RepositoryCommon<Profesor> {
    public Profesor loginProfesor(String user, String pass);
}
