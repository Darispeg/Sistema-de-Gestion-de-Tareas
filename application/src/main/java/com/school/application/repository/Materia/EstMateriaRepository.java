package com.school.application.repository.Materia;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.school.application.mapper.EstMateriaMapper;
import com.school.application.model.EstMateria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EstMateriaRepository implements EstMateriaRep {

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

    @Override
    public boolean update(EstMateria eMateria) {
        if(eMateria.getIdMateria() > 0 && eMateria.getIdEstudiante() > 0) {
            String sql = String.format("UPDATE estmateria SET estado='%d' WHERE idEstudiante = '%d' AND idMateria='%d'",
            eMateria.isEstado(), eMateria.getIdEstudiante(), eMateria.getIdMateria());
            jdbcTemplate.execute(sql);
            return true;
        }
        return false;
    }

    @Override
    public List<EstMateria> findAll(Pageable pageable) {
        return jdbcTemplate.query("SELECT * FROM estmateria", new EstMateriaMapper());
    }

    @Override
    public EstMateria findId(int id) {
        Object[] param = new Object[] {id};
        return jdbcTemplate.queryForObject("SELECT * FROM estmateria WHERE idMateria = ?", param, new EstMateriaMapper());
    }
}
