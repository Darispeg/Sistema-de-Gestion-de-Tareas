package com.school.application.repository.Curso;

import java.util.List;

import com.school.application.model.Curso;
import com.school.application.repository.RepositoryCommon;

public interface CursoRep extends RepositoryCommon<Curso> {
    public List<Curso> buscarParalelo(String paralelo);
}
