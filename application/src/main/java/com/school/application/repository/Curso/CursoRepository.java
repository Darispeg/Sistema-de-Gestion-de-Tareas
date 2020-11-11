package com.school.application.repository.Curso;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.validation.Valid;

import com.school.application.mapper.CursoMapper;
import com.school.application.model.Curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

@Repository
public class CursoRepository implements CursoRep {

    @Autowired
    private DataSource datasource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void PostConstruct(){
        jdbcTemplate = new JdbcTemplate(datasource);
    }

    public JdbcTemplate getJdbc() {
        return jdbcTemplate;
    }

    public void setJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean save(Curso curso) {
        try {
            String sql = String.format("INSERT INTO curso(idEscuela, idGrado, paralelo, gestion) VALUES ('%d', '%d', '%s', '%d')",
            curso.getIdEscuela(), curso.getIdGrado(), curso.getParalelo(), curso.getGestion());
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Curso curso) {
        if(curso.getIdCurso() > 0) {
            String sql = String.format("UPDATE curso SET paralelo='%s', gestion='%d' WHERE idCurso = '%d'", 
            curso.getParalelo(), curso.getGestion(), curso.getIdCurso());
            jdbcTemplate.execute(sql);
            return true;
        }
        return false;
    }

    @Override
    public List<Curso> findAll(Pageable pageable) {
        return jdbcTemplate.query("SELECT * FROM curso", new CursoMapper());
    }

    @Override
    public Curso findId(int id) {
        Object[] param = new Object[] {id};
        return jdbcTemplate.queryForObject("SELECT * FROM curso WHERE idCurso = ?", param, new CursoMapper());
    }

    @Override
    public List<Curso> buscarParalelo(String paralelo) {
        Object[] param = new Object[] {paralelo};
        return jdbcTemplate.query("SELECT * FROM curso WHERE paralelo = ? + '%'", param, new CursoMapper());
    }
}
