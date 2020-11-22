package com.school.application.repository.Profesor;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.school.application.mapper.ProfesorMapper;
import com.school.application.model.Profesor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProfesorRepository implements ProfesorRep {

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
    public boolean save(Profesor profesor) {
        try {
            String sql = String.format("INSERT INTO profesor(ci, idEscuela, correo, contrasena) VALUES ('%d', '%d', '%s', '%s')",
            profesor.getCi(), profesor.getIdEscuela(), profesor.getCorreo(), profesor.getContrasena());
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Profesor profesor) {
        if(profesor.getIdProfesor() > 0 || profesor.getCi() > 0) {
            String sql = String.format("UPDATE profesor SET correo='%s', contrasena='%s' WHERE ci = '%d'",
            profesor.getCorreo(), profesor.getContrasena(), profesor.getCi());
            jdbcTemplate.execute(sql);
            return true;
        }
        return false;
    }

    @Override
    public List<Profesor> findAll(Pageable pageable) {
        return jdbcTemplate.query("SELECT * FROM profesor", new ProfesorMapper());
    }

    @Override
    public Profesor findId(int id) {
        Object[] param = new Object[] {id};
        return jdbcTemplate.queryForObject("SELECT * FROM profesor WHERE idProfesor = ?", param, new ProfesorMapper());
    }

}
