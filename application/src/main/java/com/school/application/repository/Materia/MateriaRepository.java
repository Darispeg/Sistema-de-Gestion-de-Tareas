package com.school.application.repository.Materia;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.school.application.mapper.MateriaMapper;
import com.school.application.model.Materia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MateriaRepository implements MateriaRep {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    
    @PostConstruct
    public void PostConstruct(){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean save(Materia materia) {
        try {
            String sql = String.format("INSERT INTO materia(idCurso, nombre, descripcion) VALUES ('%d', '%s', '%s')",
            materia.getIdCurso(), materia.getNombre(), materia.getDescripcion());
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Materia materia) {
        if(materia.getIdMateria() > 0) {
            String sql = String.format("UPDATE materia SET idCurso='%d', nombre='%s', descripcion='%s' WHERE idMateria='%d'",
            materia.getIdMateria(), materia.getNombre(), materia.getDescripcion(), materia.getIdMateria());
            jdbcTemplate.execute(sql);
            return true;
        }
        return false;
    }

    @Override
    public List<Materia> findAll(Pageable pageable) {
        return jdbcTemplate.query("SELECT * FROM materia", new MateriaMapper());
    }

    @Override
    public Materia findId(int id) {
        Object[] param = new Object[] {id};
        return jdbcTemplate.queryForObject("SELECT * FROM materia WHERE idMateria = ?", param, new MateriaMapper());
    }
}