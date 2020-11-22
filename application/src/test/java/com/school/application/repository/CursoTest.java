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
        try {
            Curso curso = new Curso();
            curso.setIdEscuela(1);
            curso.setIdGrado(2);
            curso.setParalelo("5°A");
            curso.setGestion(2020);
    
            boolean result = repository.save(curso);
    
            if(!result)
            {
                throw new RuntimeException("\n\n\t\t***************************** No paso el test Save ***************************************** \n");
            }
            log.info("\n\n\t\t-------------------------------- Paso el test save ------------------------------------------\n");
        } catch (RuntimeException e) {
            throw new RuntimeException("\n\n\t\t***************************** No paso el test Save ***************************************** \n" + e);
        }
    }

    @Test
    public void updateTest(){
        try {
            Curso curso = new Curso();
            curso.setIdCurso(8);
            curso.setParalelo("XD");
            curso.setGestion(2021);
            boolean result = repository.update(curso);
            
            if(!result)
            {
                throw new RuntimeException("\n\n\t\t***************************** No paso el test Update ***************************************** \n");
            }
            log.info("\n\n\t\t-------------------------------- Paso el test Update ------------------------------------------\n");
        } catch (RuntimeException e) {
            throw new RuntimeException("\n\n\t\t***************************** No paso el test Update ***************************************** \n" + e);
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
            log.info("\n\n\t\t-------------------------------- Paso el test findAll ------------------------------------------\n");            
        } catch (RuntimeException e) {
            throw new RuntimeException("\n\n\t\t***************************** No paso el test findAll ***************************************** \n" + e);
        }
    }

    @Test
    public void findByIdTest(){
        try {
            Curso find = repository.findId(8);
            if(find == null){
                log.info("No se encontro ningun curso con este ID");
            }else{
                log.info(find.getIdCurso() + " : " + find.getParalelo());
            }
            log.info("\n\n\t\t-------------------------------- Paso el test findById ------------------------------------------\n");
        } catch (RuntimeException e) {
            throw new RuntimeException("\n\n\t\t***************************** No paso el test findById ***************************************** \n" + e);
        }
    }
}
