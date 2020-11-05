package com.school.application.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.application.model.ProfesorMateria;

import org.springframework.jdbc.core.RowMapper;

public class ProfesorMateriaMapper implements RowMapper<ProfesorMateria> {

    @Override
    public ProfesorMateria mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        ProfesorMateria profesor = new ProfesorMateria();

        profesor.setId(rs.getInt("id"));
        profesor.setIdMateria(rs.getInt("idMateria"));
        profesor.setIdProfesor(rs.getInt("idProfesor"));
        profesor.setEstado(rs.getBoolean("estado"));

        return profesor;
    }
    
}
