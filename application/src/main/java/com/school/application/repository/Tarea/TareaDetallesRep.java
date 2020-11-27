package com.school.application.repository.Tarea;

import java.util.List;

import com.school.application.model.TareaDetalles;

public interface TareaDetallesRep {
    
    public List<TareaDetalles> findDetalles(int idTarea);

}
