package com.school.application.repository.Tarea;


import com.school.application.model.Tarea;
import com.school.application.repository.RepositoryCommon;

public interface TareaRep extends RepositoryCommon<Tarea>{
    public boolean updateState(int id);
}
