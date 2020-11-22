package com.school.application.component;

import javax.sql.DataSource;

import com.school.application.repository.Curso.CursoRepository;
import com.school.application.repository.Escuela.EscuelaRepository;
import com.school.application.repository.Estudiante.EstudianteRepository;
import com.school.application.repository.Materia.EstMateriaRepository;
import com.school.application.repository.Materia.MateriaRepository;
import com.school.application.repository.Materia.ProfMateriaRepository;
import com.school.application.repository.Materia.TareaMateriaRepository;
import com.school.application.repository.Persona.PersonaRepository;
import com.school.application.repository.Profesor.ProfesorRepository;
import com.school.application.repository.Tarea.DetalleTareaRepository;
import com.school.application.repository.Tarea.TareaRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
/* import org.springframework.stereotype.Component;
// Habilitar el comentario para empezar el Test
@Component */
public class TestDatabaseConfiguration {
    
    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/escuela_test?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("q.yuD2EZF9iipkR");

        return dataSource;
    }

    @Bean
    private EscuelaRepository escuelaRepository(){
        return new EscuelaRepository();
    } 

    @Bean
    private PersonaRepository personaRepository(){
        return new PersonaRepository();
    }

    @Bean
    private CursoRepository cursoRepository(){
        return new CursoRepository();
    }

    @Bean
    private MateriaRepository materiaRepository(){
        return new MateriaRepository();
    }

    @Bean
    private ProfesorRepository profesorRepository(){
        return new ProfesorRepository();
    }

    @Bean
    private EstudianteRepository estudianteRepository(){
        return new EstudianteRepository();
    }

    @Bean
    private EstMateriaRepository estMateriaRepository(){
        return new EstMateriaRepository();
    }

    @Bean
    private ProfMateriaRepository profMateriaRepository(){
        return new ProfMateriaRepository();
    }

    @Bean
    private TareaMateriaRepository tareaMateriaRepository(){
        return new TareaMateriaRepository();
    }

    @Bean 
    private TareaRepository tareaRepository(){
        return new TareaRepository();
    }

    @Bean
    private DetalleTareaRepository detalleTareaRepository(){
        return new DetalleTareaRepository();
    }
}
