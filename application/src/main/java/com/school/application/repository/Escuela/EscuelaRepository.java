package com.school.application.repository.Escuela;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.school.application.mapper.EscuelaMapper;
import com.school.application.model.Escuela;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EscuelaRepository implements EscuelaRep {

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
    public boolean save(Escuela escuela) {
        try {
            String sql = String.format("INSERT INTO escuela(nombre, descripcion, telefono, correo, contrasena)"+
            " VALUES ('%s', '%s', '%s', '%s', '%s')", escuela.getNombre(), escuela.getDescripcion(), 
            escuela.getTelefono(), escuela.getCorreo(), escuela.getContrasena());
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Escuela escuela) {
        if(escuela.getIdEscuela() > 0) {
            String sql = String.format("UPDATE escuela SET nombre='%s', descripcion='%s', telefono='%s', correo='%s', contrasena='%s' WHERE idEscuela = '%d'",
            escuela.getNombre(), escuela.getDescripcion(), escuela.getTelefono(), escuela.getCorreo(), escuela.getContrasena(), escuela.getIdEscuela());
            jdbcTemplate.execute(sql);
            return true;
        }
        return false;
    }

    @Override
    public List<Escuela> findAll(Pageable pageable) {
        return jdbcTemplate.query("SELECT * FROM escuela", new EscuelaMapper());
    }

    @Override
    public Escuela findId(int id) {
        Object[] param = new Object[] {id};
        return jdbcTemplate.queryForObject("SELECT * FROM escuela WHERE idEscuela = ?", param, new EscuelaMapper());
    }
}
