package com.school.application.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.application.model.ProfesorMateria;

import org.springframework.jdbc.core.RowMapper;

                    /* Mapper VISTA Materia-Profesor */
/* https://lucid.app/lucidchart/11125235-04a3-4dde-b178-b0e04c149384/edit?page=EedOYkK~5wn-#?folder_id=home&browser=icon */

// Mapeado de la tabla que obtenemos del procedimiento : call sp_profesorMateria( ciProfesor = ? );;

public class ProfesorMateriaMapper implements RowMapper<ProfesorMateria> {

    @Override
    public ProfesorMateria mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        ProfesorMateria profesor = new ProfesorMateria();

        profesor.setId(rs.getInt("id"));
        profesor.setIdMateria(rs.getInt("idMateria"));
        profesor.setMateria(rs.getString("materia"));
        profesor.setParalelo(rs.getString("paralelo"));
        profesor.setIdProfesor(rs.getInt("idProfesor"));
        profesor.setCi(rs.getInt("ci"));
        profesor.setEstado(rs.getBoolean("estado"));

        return profesor;
    }    
}
