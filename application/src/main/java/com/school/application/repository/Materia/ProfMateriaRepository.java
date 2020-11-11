package com.school.application.repository.Materia;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.school.application.mapper.ProfesorMateriaMapper;
import com.school.application.model.ProfesorMateria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProfMateriaRepository implements ProfMateriaRep {

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
    public boolean save(ProfesorMateria profMat) {
        try {
            String sql = String.format("INSERT INTO profesormateria(idMateria, idProfesor, estado) VALUES ('%d', '%d', true)",
            profMat.getIdMateria(), profMat.getIdProfesor());
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(ProfesorMateria profMat) {
        if(profMat.getIdMateria() > 0 && profMat.getIdProfesor() > 0) {
            String sql = String.format("UPDATE profesormateria SET estado = !estado WHERE idProfesor ='%d'  AND idMateria ='%d'",
            profMat.getIdProfesor(), profMat.getIdMateria());
            jdbcTemplate.execute(sql);
            return true;
        }
        return false;
    }

    @Override
    public List<ProfesorMateria> findAll(Pageable pageable) {
        return jdbcTemplate.query("call listar_profMateria()", new ProfesorMateriaMapper());
    }

    @Override
    public ProfesorMateria findId(int id) {
        return null;
    }

    @Override
    public List<ProfesorMateria> findMateriasProfesor(int idProfesor){
        Object[] param = new Object[] {idProfesor};
        return jdbcTemplate.query("call SP_profesor_materia(?)", param, new ProfesorMateriaMapper());
    }

    @Override
    public boolean delete(int idProfesor, int idMateria) {
        try {
            String sql = String.format("DELETE FROM profesormateria WHERE idProfesor = '%d' AND idMateria = '%d'", 
                idProfesor, idMateria);
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
