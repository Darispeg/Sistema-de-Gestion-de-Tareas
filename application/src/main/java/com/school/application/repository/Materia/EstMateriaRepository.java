package com.school.application.repository.Materia;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.school.application.mapper.EstMateriaMapper;
import com.school.application.model.vistaModel.EstMateria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
                    /* VISTA Materia-Estudiante */
/* https://lucid.app/lucidchart/11125235-04a3-4dde-b178-b0e04c149384/edit?page=QIcOlET8ieVq#?folder_id=home&browser=icon */

@Repository
public class EstMateriaRepository implements EstMateriaRep {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void PostConstruct(){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Funcion para Insertar o Registrar un nuevo estudiante con una materia */
    @Override
    public boolean save(EstMateria eMateria) {
        try {
            String sql = String.format("INSERT INTO estmateria(idEstudiante, idMateria) VALUES ('%d', '%d')",
            eMateria.getIdEstudiante(), eMateria.getIdMateria());
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* Funcion para cambiar el estado de materia de un estudiante */
    @Override
    public boolean update(EstMateria eMateria) {
        if(eMateria.getIdMateria() > 0 && eMateria.getIdEstudiante() > 0) {
            String sql = String.format("UPDATE estmateria SET estado = !estado WHERE idEstudiante = '%d' AND idMateria='%d'",
            eMateria.getIdEstudiante(), eMateria.getIdMateria());
            jdbcTemplate.execute(sql);
            return true;
        }
        return false;
    }

    /* Funcion que devuelve todos las materias en las que esta registrado un estudiante */
    @Override
    public List<EstMateria> findEstudiante(int ci) {
        Object[] param = new Object[] {ci};
        return jdbcTemplate.query("call sp_estudianteMateria(?)", param, new EstMateriaMapper());
    }
}