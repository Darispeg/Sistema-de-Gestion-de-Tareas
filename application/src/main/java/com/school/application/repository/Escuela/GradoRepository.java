package com.school.application.repository.Escuela;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.school.application.mapper.GradoMapper;
import com.school.application.model.Grado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GradoRepository implements GradoRep {

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
    public boolean save(Grado grado) {
        try {
            String sql = String.format("INSERT INTO grado(grado) VALUES ('%s')",
            grado.getGrado());
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Grado grado) {
        if(grado.getIdGrado() > 0) {
            String sql = String.format("UPDATE grado SET grado = '%s' WHERE idGrado = '%d'", grado.getGrado(), grado.getIdGrado());
            jdbcTemplate.execute(sql);
            return true;
        }
        return false;
    }

    @Override
    public List<Grado> findAll(Pageable pageable) {
        return jdbcTemplate.query("SELECT * FROM grado", new GradoMapper());
    }

    @Override
    public Grado findId(int id) {
        Object[] param = new Object[] {id};
        return jdbcTemplate.queryForObject("SELECT * FROM grado WHERE idGrado = ?", param, new GradoMapper());
    }

}
