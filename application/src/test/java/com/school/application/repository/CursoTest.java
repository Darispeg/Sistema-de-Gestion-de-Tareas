package com.school.application.repository;

import java.util.List;

import com.school.application.component.TestDatabaseConfiguration;
import com.school.application.model.Curso;
import com.school.application.repository.Curso.CursoRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
public class CursoTest {
    
    @Autowired
    private CursoRepository repository;

    Log log = LogFactory.getLog(getClass());

    @Test
    public void saveTest(){
        Curso curso = new Curso();
        curso.setIdEscuela(1);
        curso.setIdCurso(1);
        curso.setParalelo("update");
        curso.setGestion(2021);

        boolean result = repository.save(curso);

        if(!result)
        {
            log.error("No se realizo ningun cambio en la tabla Curso: " + result);
        }
    }

    @Test
    public void updateTest(){
        Curso curso = new Curso();
        curso.setIdCurso(18);
        curso.setParalelo("Update Test");
        curso.setGestion(2021);
        boolean result = repository.save(curso);

        if(!result)
        {
            log.error("No se realizo ningun cambio en la tabla Curso: " + result);
        }
    }

    @Test
    public void findAllTest(){
        try {
            SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
            List<Curso> all = repository.findAll(pageable);
            if(all.isEmpty()){
                log.info("No tiene ningun elemento de esta tabla cargado en la base de datos");
            }else{
                for (Curso curso : all) {
                    log.info(curso.getIdCurso() + " : " + curso.getParalelo());
                }            
            }            
        } catch (Exception e) {
            log.error("Error en el test FindAll Curso");
        }
    }

    @Test
    public void findByIdTest(){
        try {
            Curso find = repository.findId(28);
            if(find == null){
                log.info("No se encontro ningun curso con este ID");
            }else{
                log.info(find.getIdCurso() + " : " + find.getParalelo());
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al ejecutarse el Test findById Curso " + e);
        }
    }
}
