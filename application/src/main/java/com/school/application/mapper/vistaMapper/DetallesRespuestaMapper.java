package com.school.application.mapper.vistaMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.application.model.RespuestaTarea;

import org.springframework.jdbc.core.RowMapper;

public class DetallesRespuestaMapper implements RowMapper<RespuestaTarea> {

    @Override
    public RespuestaTarea mapRow(ResultSet rs, int rowNum) throws SQLException {
        RespuestaTarea rTarea = new RespuestaTarea();

        rTarea.setIdTarea(rs.getInt("idTarea"));
        rTarea.setTitulo(rs.getString("titulo"));
        rTarea.setIdEstudiante(rs.getInt("idEstudiante"));
        rTarea.setNombre(rs.getString("nombre"));
        rTarea.setaPaterno(rs.getString("aPaterno"));
        rTarea.setFechaRespuesta(rs.getDate("fechaRespuesta"));
        rTarea.setDocumento(rs.getString("documento"));
        rTarea.setUrl(rs.getString("url"));
        rTarea.setNota(rs.getFloat("nota"));
        rTarea.setComentario(rs.getString("comentario"));

        return rTarea;
    }
}
