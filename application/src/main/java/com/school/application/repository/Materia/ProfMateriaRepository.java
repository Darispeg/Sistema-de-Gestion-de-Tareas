package com.school.application.repository.Materia;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.school.application.mapper.ProfesorMateriaMapper;
import com.school.application.model.vistaModel.ProfesorMateria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

                    /* VISTA Materia-Profesor */
/* https://lucid.app/lucidchart/11125235-04a3-4dde-b178-b0e04c149384/edit?page=EedOYkK~5wn-#?folder_id=home&browser=icon */

@Repository
public class ProfMateriaRepository implements ProfMateriaRep {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void PostConstruct(){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Funcion para Insertar o Registrar a un Nuevo Profesor en una Materia */
    @Override
    public boolean save(ProfesorMateria profMat) {
        try {
            String sql = String.format("INSERT INTO profesormateria(idMateria, idProfesor) VALUES ('%d', '%d')",
            profMat.getIdMateria(), profMat.getIdProfesor());
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* Funcion para cambiar el estado de materia de un Profesor */
    @Override
    public boolean update(ProfesorMateria profMat) {
        if(profMat.getIdMateria() > 0 && profMat.getIdProfesor() > 0) {
            String sql = String.format("UPDATE profesormateria SET estado = !estado WHERE idProfesor ='%d'  AND idMateria ='%d'",
            profMat.getIdProfesor(), profMat.getIdMateria());
            jdbcTemplate.execute(sql);
            return true;
        }
        return false;
    }

    /* Funcion que devuelve todas las materias que imparte un Profesor */
    @Override
    public List<ProfesorMateria> findMateriasProfesor(int ci){
        Object[] param = new Object[] {ci};
        return jdbcTemplate.query("call sp_profesorMateria(?)", param, new ProfesorMateriaMapper());
    }

    /* Funcion para eliminar */
    @Override
    public boolean delete(int idProfesor, int idMateria) {
        try {
            String sql = String.format("DELETE FROM profesormateria WHERE idProfesor = '%d' AND idMateria = '%d'", 
                idProfesor, idMateria);
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
