package com.school.application.repository.Tarea.respuestas;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.school.application.mapper.RespuestaMapper;
import com.school.application.model.RespuestaTarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RespuestaRepository implements RespuestaRep {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean save(RespuestaTarea Object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update(RespuestaTarea Object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<RespuestaTarea> findAll(Pageable pageable) {
        return jdbcTemplate.query("SELECT * FROM respuestatarea", new RespuestaMapper());
    }

    @Override
    public RespuestaTarea findId(int id) {
        // TODO Auto-generated method stub
        return null;
    }
}
