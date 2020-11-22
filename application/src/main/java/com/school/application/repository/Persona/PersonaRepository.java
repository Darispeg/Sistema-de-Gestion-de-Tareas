package com.school.application.repository.Persona;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.school.application.mapper.PersonaMapper;
import com.school.application.model.Persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonaRepository implements PersonaRep {

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
    public boolean save(Persona persona) {
        try {
            String sql = String.format("INSERT INTO persona (nombre, aPaterno, aMaterno, edad, sexo, telefono) " +
            "VALUES ('%s', '%s', '%s', '%d','%s','%s')",
            persona.getNombre(), persona.getaPaterno(), persona.getaMaterno(), persona.getEdad(), persona.getSexo(),
            persona.getTelefono());
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Persona persona) {
        try {
            String sql = String.format("UPDATE persona SET nombre='%s', aPaterno='%s', " +
            "aMaterno='%s', edad='%d', sexo='%s', telefono='%s' WHERE ci='%d'",
            persona.getNombre(), persona.getaPaterno(), persona.getaMaterno(), persona.getEdad(), persona.getSexo(),
            persona.getTelefono(),persona.getCi());
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Persona> findAll(Pageable pageable) {
        return jdbcTemplate.query("SELECT * FROM persona", new PersonaMapper());
    }

    @Override
    public Persona findId(int id) {
        Object[] param = new Object[] {id};
        return jdbcTemplate.queryForObject("SELECT * FROM persona WHERE ci = ?", param, new PersonaMapper());
    }

}
