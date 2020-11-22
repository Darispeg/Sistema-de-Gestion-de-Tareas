package com.school.application.repository.Tarea.respuestas;

import java.util.List;

import com.school.application.model.RespuestaTarea;

public interface RespuestaRep  {
    public List<RespuestaTarea> findAllRespuestas(int idTarea);

    public List<RespuestaTarea> findRespuestaEstudiante(int idTarea, int idEstudiante);

    public boolean calificarTarea(RespuestaTarea rTarea);
}
