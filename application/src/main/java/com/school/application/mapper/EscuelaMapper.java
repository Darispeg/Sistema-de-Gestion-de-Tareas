package com.school.application.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.application.model.Escuela;

import org.springframework.jdbc.core.RowMapper;

public class EscuelaMapper implements RowMapper<Escuela> {

    @Override
    public Escuela mapRow(ResultSet rs, int rowNum) throws SQLException {
        Escuela escuela = new Escuela();

        escuela.setIdEscuela(rs.getInt("idEscuela"));
        escuela.setNombre(rs.getString("nombre"));
        escuela.setDescripcion(rs.getString("descripcion"));
        escuela.setTelefono(rs.getString("telefono"));
        escuela.setCorreo(rs.getString("correo"));
        escuela.setContrasena(rs.getString("contrasena"));

        return escuela;
    }
    
}
