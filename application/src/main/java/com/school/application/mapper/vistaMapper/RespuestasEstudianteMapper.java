package com.school.application.mapper.vistaMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.application.model.RespuestaTarea;

import org.springframework.jdbc.core.RowMapper;

public class RespuestasEstudianteMapper implements RowMapper<RespuestaTarea> {

    @Override
    public RespuestaTarea mapRow(ResultSet rs, int rowNum) throws SQLException {
        RespuestaTarea rTarea = new RespuestaTarea();

        rTarea.setIdTarea(rs.getInt("idTarea"));
        rTarea.setIdEstudiante(rs.getInt("idEstudiante"));
        rTarea.setNombre(rs.getString("nombre"));
        rTarea.setaPaterno(rs.getString("aPaterno"));
        rTarea.setaMaterno(rs.getString("aMaterno"));

        return rTarea;
    }
}