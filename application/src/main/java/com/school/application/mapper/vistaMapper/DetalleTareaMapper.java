package com.school.application.mapper.vistaMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.application.model.vistaModel.DetalleTarea;

import org.springframework.jdbc.core.RowMapper;

            /* VISTA Detalle - Tarea  */
/* https://lucid.app/lucidchart/11125235-04a3-4dde-b178-b0e04c149384/edit?page=dHdO1Vea6BbX#?folder_id=home&browser=icon */

public class DetalleTareaMapper implements RowMapper<DetalleTarea> {

    @Override
    public DetalleTarea mapRow(ResultSet rs, int rowNum) throws SQLException {
        DetalleTarea dTarea = new DetalleTarea();

        dTarea.setIdDetalle(rs.getInt("idDetalle"));
        dTarea.setIdTarea(rs.getInt("idTarea"));
        dTarea.setTitulo(rs.getString("titulo"));
        dTarea.setDescripcion(rs.getString("descripcion"));
        dTarea.setFechaInicio(rs.getDate("fechaInicio"));
        dTarea.setFechaFinal(rs.getDate("fechaFinal"));
        dTarea.setDocumento(rs.getString("documento"));
        dTarea.setUrl(rs.getString("url"));

        return dTarea;
    }
}
