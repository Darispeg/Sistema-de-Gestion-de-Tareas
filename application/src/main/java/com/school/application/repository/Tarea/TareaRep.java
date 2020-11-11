package com.school.application.repository.Tarea;

import java.sql.Date;

import com.school.application.model.Tarea;
import com.school.application.repository.RepositoryCommon;

public interface TareaRep extends RepositoryCommon<Tarea>{
    public boolean dateUpdate(int id, Date date);
    public boolean urlUpdate(int id, String url);
}
