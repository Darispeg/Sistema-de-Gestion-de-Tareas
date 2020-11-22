package com.school.application.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.application.model.Estudiante;

import org.springframework.jdbc.core.RowMapper;

public class EstudianteMapper implements RowMapper<Estudiante> {

    @Override
    public Estudiante mapRow(ResultSet rs, int rowNum) throws SQLException {
        Estudiante estudiante = new Estudiante();
        
        estudiante.setIdEstudiante(rs.getInt("idEstudiante"));
        estudiante.setCi(rs.getLong("ci"));
        estudiante.setIdCurso(rs.getInt("idCurso"));
        estudiante.setCorreo(rs.getString("correo"));
        estudiante.setContrasena(rs.getString("contrasena"));
        
        return estudiante;
    }
}
