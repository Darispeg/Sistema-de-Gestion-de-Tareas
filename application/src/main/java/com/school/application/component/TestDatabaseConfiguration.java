package com.school.application.component;

import javax.sql.DataSource;

import com.school.application.repository.Curso.CursoRepository;
import com.school.application.repository.Persona.PersonaRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
/* import org.springframework.stereotype.Component;

@Component */
public class TestDatabaseConfiguration {
    
    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/testschool?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("q.yuD2EZF9iipkR");

        return dataSource;
    }

    @Bean
    private PersonaRepository personaRepository(){
        return new PersonaRepository();
    }

    @Bean
    private CursoRepository cursoRepository(){
        return new CursoRepository();
    }
}
