package com.school.application.repository.Tarea;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.school.application.mapper.TareaMapper;
import com.school.application.model.Tarea;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TareaRepository implements TareaRep {

    Log log = LogFactory.getLog(getClass());

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
    public boolean save(Tarea tarea) {
        try {
            String fecha = tarea.getFechaFinal().toString();
            String sql = String.format(
                    "INSERT INTO tarea (idCurso, idMateria, titulo, descripcion, fechaFinal) "
                            + "VALUES ('%d', '%d', '%s', '%s', '%s')",
                    tarea.getIdCurso(), tarea.getIdMateria(), tarea.getTitulo(), tarea.getDescripcion(), fecha);
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Tarea tarea) {
        if (tarea.getIdTarea() > 0) {
            String fecha = tarea.getFechaFinal().toString();
            String sql = String.format(
                    "UPDATE tarea SET titulo = '%s', descripcion = '%s', fechaFinal = '%s' WHERE idTarea = '%d'",
                    tarea.getTitulo(), tarea.getDescripcion(), fecha, tarea.getIdTarea());
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
        Object[] param = new Object[] { id };
        return jdbcTemplate.queryForObject("SELECT * FROM tarea WHERE idTarea = ?", param, new TareaMapper());
    }

    @Override
    public boolean updateState(int id) {
        if (id > 0) {
            String sql = String.format(
                    "UPDATE tarea SET estado = !estado WHERE idTarea = '%d'", id);
            jdbcTemplate.execute(sql);
            return true;
        }
        return false;
    }
}
