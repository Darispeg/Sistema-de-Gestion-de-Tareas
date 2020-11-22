package com.school.application.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.application.model.Grado;

import org.springframework.jdbc.core.RowMapper;

public class GradoMapper implements RowMapper<Grado> {



    @Override
    public Grado mapRow(ResultSet rs, int rowNum) throws SQLException {
        Grado grado = new Grado();

        grado.setIdGrado(rs.getInt("idGrado"));
        grado.setGrado(rs.getString("grado"));

        return grado;
    }
}
