package com.school.application.mapper.vistaMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.application.model.vistaModel.TareaMateria;

import org.springframework.jdbc.core.RowMapper;

    /* Vista Tarea Materia Estudiante */
/* https://lucid.app/lucidchart/11125235-04a3-4dde-b178-b0e04c149384/edit?page=7hdOQH08_l-T#?folder_id=home&browser=icon */
    
    /* Vista Tarea Materia Profesor */
/* https://lucid.app/lucidchart/11125235-04a3-4dde-b178-b0e04c149384/edit?page=~XdOBMzCrmb.#?folder_id=home&browser=icon */

// Mapeado de la tabla que obtenemos del procedimiento : call sp_tareasMateria( idMateria = ? );;
public class TareaMateriaMapper implements RowMapper<TareaMateria> {

    @Override
    public TareaMateria mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        TareaMateria tMateria = new TareaMateria();

        tMateria.setParalelo(rs.getString("paralelo"));
        tMateria.setIdTarea(rs.getInt("idTarea"));
        tMateria.setIdMateria(rs.getInt("idMateria"));
        tMateria.setNombre(rs.getString("nombre"));
        tMateria.setTitulo(rs.getString("titulo"));
        tMateria.setDescripcion(rs.getString("descripcion"));
        tMateria.setEstado(rs.getBoolean("estado"));

        return tMateria;
    }
    
}
