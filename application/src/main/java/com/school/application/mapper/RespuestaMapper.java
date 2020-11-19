package com.school.application.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.application.model.RespuestaTarea;

import org.springframework.jdbc.core.RowMapper;

public class RespuestaMapper implements RowMapper<RespuestaTarea> {

    @Override
    public RespuestaTarea mapRow(ResultSet rs, int rowNum) throws SQLException {
        RespuestaTarea respuesta = new RespuestaTarea();
        
        respuesta.setIdRespuesta(rs.getInt("idRespuesta"));
        respuesta.setIdTarea(rs.getInt("idTarea"));
        respuesta.setIdEstudiante(rs.getInt("idEstudiante"));
        respuesta.setFechaRespuesta(rs.getDate("fechaRespuesta"));
        respuesta.setDocumento(rs.getString("documento"));
        respuesta.setUrl(rs.getString("url"));
        respuesta.setNota(rs.getFloat("nota"));
        respuesta.setComentario(rs.getString("comentario"));

        return respuesta;
    }
    
}
