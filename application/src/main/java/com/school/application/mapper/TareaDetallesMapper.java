package com.school.application.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.application.model.TareaDetalles;

import org.springframework.jdbc.core.RowMapper;

public class TareaDetallesMapper implements RowMapper<TareaDetalles> {

    @Override
    public TareaDetalles mapRow(ResultSet rs, int rowNum) throws SQLException {
        TareaDetalles tareaDetalles = new TareaDetalles();
        tareaDetalles.setIdDetalle(rs.getInt("idDetalle"));
        tareaDetalles.setIdTarea(rs.getInt("idTarea"));
        tareaDetalles.setDocumento(rs.getString("documento"));
        tareaDetalles.setUrl(rs.getString("url"));
        return tareaDetalles;
    }
    
}
