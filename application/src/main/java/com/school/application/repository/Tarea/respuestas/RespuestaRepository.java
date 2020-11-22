package com.school.application.repository.Tarea.respuestas;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.school.application.mapper.vistaMapper.DetallesRespuestaMapper;
import com.school.application.mapper.vistaMapper.RespuestasEstudianteMapper;
import com.school.application.model.RespuestaTarea;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<RespuestaTarea> findAllRespuestas(int idTarea) {
        Object[] param = new Object[]{idTarea}; 
        return jdbcTemplate.query("call sp_respuestaTarea(?)" , param, new RespuestasEstudianteMapper());
    }

    Log log = LogFactory.getLog(getClass());

    @Override
    public List<RespuestaTarea> findRespuestaEstudiante(int idTarea, int idEstudiante) {
        try {
            log.info("idTarea: " + idTarea + " idEstudiante: " + idEstudiante);
            String sql = String.format("call sp_respuestaEstudiante('%d', '%d')", idEstudiante, idTarea);
            return jdbcTemplate.query(sql, new DetallesRespuestaMapper());
        } catch (Exception e) {
            log.info(e);
            return null;
        }
    }

    @Override
    public boolean calificarTarea(RespuestaTarea rTarea) {
        try {
            if (rTarea.getIdTarea() > 0) {
                String sql = String.format("call sp_calificar('%d', '%d'," + rTarea.getNota() +", '%s')"
                , rTarea.getIdTarea(), rTarea.getIdEstudiante(), rTarea.getComentario());
                jdbcTemplate.execute(sql);
                return true;
            }
            return false;            
        } catch (Exception exception) {
            log.info(exception);
            return false;
        }
    }
}
