package com.school.application.repository.Materia;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.school.application.mapper.vistaMapper.TareaMateriaMapper;
import com.school.application.model.vistaModel.TareaMateria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

    /* Vista Tarea Materia Estudiante */
/* https://lucid.app/lucidchart/11125235-04a3-4dde-b178-b0e04c149384/edit?page=7hdOQH08_l-T#?folder_id=home&browser=icon */
    
    /* Vista Tarea Materia Profesor */
/* https://lucid.app/lucidchart/11125235-04a3-4dde-b178-b0e04c149384/edit?page=~XdOBMzCrmb.#?folder_id=home&browser=icon */


@Repository
public class TareaMateriaRepository implements TareaMateriaRep {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Funcion para mostrar todas las tareas que tiene una materia
    @Override
    public List<TareaMateria> findTarea(int materia) {
        Object[] param = new Object[]{materia};
        return jdbcTemplate.query("call sp_tareasMateria(?)", param, new TareaMateriaMapper());
    }


}
