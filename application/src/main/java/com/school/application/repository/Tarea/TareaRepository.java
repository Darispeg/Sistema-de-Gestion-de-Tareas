package com.school.application.repository.Tarea;

import java.sql.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.school.application.mapper.TareaMapper;
import com.school.application.model.Tarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TareaRepository implements TareaRep {
    
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void postConstruct(){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean save(Tarea tarea) {
        try {
            String sql = String.format("INSERT INTO tarea "+
            "(idCurso, idMateria, descripcion, fechaFinal, estado) "+
            "VALUES ('%d', '%d', '%s', '%tF', true)",
            tarea.getIdTarea(),tarea.getIdMateria(), tarea.getDescripcion(), tarea.getFechaFinal());
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Tarea tarea) {
        if(tarea.getIdTarea() > 0){
            String sql = String.format("UPDATE tarea SET descripcion = '%s' WHERE idTarea = '%d'",
                tarea.getDescripcion(), tarea.getIdTarea());
            jdbcTemplate.execute(sql);
            return true;
        }
        return false;
    }

    @Override
    public List<Tarea> findAll(Pageable pageable) {
        return jdbcTemplate.query("SELECT * FROM tarea", new TareaMapper());
    }

    @Override
    public Tarea findId(int id) {
        Object[] param = new Object[] {id};
        return jdbcTemplate.queryForObject("SELECT * FROM tarea WHERE idTarea = ?", param, new TareaMapper());
    }

    @Override
    public boolean dateUpdate(int id, Date date) {
        if(id > 0){
            String sql = String.format("UPDATE tarea SET fechaFinal = '%t' WHERE idTarea = '%d'", date, id);
            jdbcTemplate.execute(sql);
            return true;
        }
        return false;
    }

    @Override
    public boolean urlUpdate(int id, String url) {
        if(id > 0){
            String sql = String.format("UPDATE tarea SET url = '%s' WHERE idTarea = '%d'", url, id);
            jdbcTemplate.execute(sql);
            return true;
        }
        return false;
    }
}
