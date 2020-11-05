package com.school.application.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.application.model.EstMateria;

import org.springframework.jdbc.core.RowMapper;

public class EstMateriaMapper implements RowMapper<EstMateria> {

    @Override
    public EstMateria mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        EstMateria estudiante = new EstMateria();

        estudiante.setId(rs.getInt("id"));
        estudiante.setIdEstudiante(rs.getInt("idEstudiante"));
        estudiante.setIdMateria(rs.getInt("idMateria"));
        estudiante.setEstado(rs.getBoolean("estado"));

        return estudiante;
    }
}
