package com.school.application.repository.Tarea;


import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.school.application.mapper.vistaMapper.DetalleTareaMapper;
import com.school.application.model.vistaModel.DetalleTarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DetalleTareaRepository {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void postConstruct(){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public DetalleTarea findTarea(int tarea){
        Object[] param = new Object[]{tarea};
        return jdbcTemplate.queryForObject("call sp_detalleTarea(?)", param, new DetalleTareaMapper());
    }
}
