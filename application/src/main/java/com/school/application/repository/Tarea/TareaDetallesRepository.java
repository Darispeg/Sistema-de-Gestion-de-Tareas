package com.school.application.repository.Tarea;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.school.application.mapper.TareaDetallesMapper;
import com.school.application.model.TareaDetalles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TareaDetallesRepository implements TareaDetallesRep {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<TareaDetalles> findDetalles(int idTarea) {
        Object[] param = new Object[] { idTarea };
        return jdbcTemplate.query("SELECT * FROM tareadetalle WHERE idTarea = ?", param, new TareaDetallesMapper());
    }
}
