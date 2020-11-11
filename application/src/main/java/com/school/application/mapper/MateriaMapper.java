package com.school.application.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.application.model.Materia;

import org.springframework.jdbc.core.RowMapper;

public class MateriaMapper implements RowMapper<Materia> {

    @Override
    public Materia mapRow(ResultSet rs, int rowNum) throws SQLException {

        Materia materia = new Materia();

        materia.setIdMateria(rs.getInt("idMateria"));
        materia.setIdCurso(rs.getInt("idCurso"));
        materia.setNombre(rs.getString("nombre"));
        materia.setDescripcion(rs.getString("descripcion"));

        return materia;
    }
    
}
