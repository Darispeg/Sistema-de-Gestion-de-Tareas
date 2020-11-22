package com.school.application.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.application.model.Persona;

import org.springframework.jdbc.core.RowMapper;

public class PersonaMapper implements RowMapper<Persona> {

    @Override
    public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        Persona persona = new Persona();

        persona.setCi(rs.getLong("ci"));
        persona.setNombre(rs.getString("nombre"));
        persona.setaPaterno(rs.getString("aPaterno"));
        persona.setaMaterno(rs.getString("aMaterno"));
        persona.setEdad(rs.getInt("edad"));
        persona.setSexo(rs.getString("sexo"));
        persona.setTelefono(rs.getString("telefono"));

        return persona;
    }
}
