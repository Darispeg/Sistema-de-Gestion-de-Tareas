package com.school.application.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.application.model.vistaModel.EstMateria;

import org.springframework.jdbc.core.RowMapper;

                    /* Mapper VISTA Materia-Estudiante */
/* https://lucid.app/lucidchart/11125235-04a3-4dde-b178-b0e04c149384/edit?page=QIcOlET8ieVq#?folder_id=home&browser=icon */

// Mapeado de la tabla que obtenemos del procedimiento : call sp_estudianteMateria( ci = ? );
public class EstMateriaMapper implements RowMapper<EstMateria> {

    @Override
    public EstMateria mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        EstMateria estudiante = new EstMateria();

        estudiante.setId(rs.getInt("id"));
        estudiante.setIdMateria(rs.getInt("idMateria"));
        estudiante.setMateria(rs.getString("materia"));
        estudiante.setCiEstudiante(rs.getInt("ciEstudiante"));
        estudiante.setIdEstudiante(rs.getInt("idEstudiante"));
        estudiante.setEstado(rs.getBoolean("estado"));

        return estudiante;
    }
}