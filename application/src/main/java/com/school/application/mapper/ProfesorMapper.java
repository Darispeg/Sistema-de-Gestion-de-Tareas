package com.school.application.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.application.model.Profesor;

import org.springframework.jdbc.core.RowMapper;

public class ProfesorMapper implements RowMapper<Profesor> {

    @Override
    public Profesor mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        Profesor profesor = new Profesor();

        profesor.setIdProfesor(rs.getInt("idProfesor"));
        profesor.setCi(rs.getLong("ci"));
        profesor.setIdEscuela(rs.getInt("idEscuela"));
        profesor.setCorreo(rs.getString("correo"));
        profesor.setContrasena(rs.getString("contrasena"));

        return profesor;
    }
    
}
