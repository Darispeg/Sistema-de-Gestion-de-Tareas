package com.school.application.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.application.model.Tarea;

import org.springframework.jdbc.core.RowMapper;

public class TareaMapper implements RowMapper<Tarea> {

    @Override
    public Tarea mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tarea tarea = new Tarea();
        
        tarea.setIdTarea(rs.getInt("idTarea"));
        tarea.setIdCurso(rs.getInt("idCurso"));
        tarea.setIdMateria(rs.getInt("idMateria"));
        tarea.setTitulo(rs.getString("titulo"));
        tarea.setDescripcion(rs.getString("descripcion"));
        tarea.setFechaInicio(rs.getDate("fechaInicio"));
        tarea.setFechaFinal(rs.getDate("fechaFinal"));
        tarea.setEstado(rs.getBoolean("estado"));

        return tarea;
    }
    
}
