package com.school.application.repository.Estudiante;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.school.application.mapper.EstudianteMapper;
import com.school.application.model.Estudiante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EstudianteRepository implements EstudianteRep {

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
    public boolean save(Estudiante estudiante) {
        try {
            String sql = String.format("insert into estudiante (ci, idCurso, correo, contrasena) VALUES ('%d', '%d', '%s', '%s')",
                        estudiante.getCi(), estudiante.getIdCurso(), estudiante.getCorreo(), estudiante.getContrasena());
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Estudiante estudiante) {
        if(estudiante.getCi() > 0){
            String sql = String.format("UPDATE estudiante SET correo='%s', contrasena='%s' WHERE ci = '%d'",
                        estudiante.getCorreo(), estudiante.getContrasena(), estudiante.getCi());
            jdbcTemplate.execute(sql);
            return true;
        }
        return false;
    }

    @Override
    public List<Estudiante> findAll(Pageable pageable) {
        return jdbcTemplate.query("SELECT * FROM estudiante", new EstudianteMapper());
    }

    @Override
    public Estudiante findId(int id) {
        Object[] param = new Object[] {id};
        return jdbcTemplate.queryForObject("SELECT * FROM estudiante WHERE ci = ?", param, new EstudianteMapper());
    }

    /* Para el Login Estudiante */
    @Override
    public Estudiante login(String usuario, String contrasena) {
        String sql = String.format("call loginEstudiante('%s', '%s')", usuario, contrasena);
        return jdbcTemplate.queryForObject(sql, new EstudianteMapper());
    }
}
