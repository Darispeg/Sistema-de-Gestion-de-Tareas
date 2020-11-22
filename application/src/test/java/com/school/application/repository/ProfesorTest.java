package com.school.application.repository;

import java.util.List;

import com.school.application.component.TestDatabaseConfiguration;
import com.school.application.model.Profesor;
import com.school.application.repository.Profesor.ProfesorRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
public class ProfesorTest {
    
    @Autowired
    private ProfesorRepository repository;

    Log log = LogFactory.getLog(getClass());

    @Test
    public void saveTest(){
        try {
            Profesor profesor = new Profesor(); //ci, idEscuela, correo, contrasena
            profesor.setCi(127);
            profesor.setIdEscuela(1);
            profesor.setCorreo("new@gmail.com");
            profesor.setContrasena("NUEVO");
    
            boolean result = repository.save(profesor);
    
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
            Profesor profesor = new Profesor();
            profesor.setCi(126);
            profesor.setCorreo("darispegdennis@gmail.com");
            profesor.setContrasena("DENNIS");

            boolean result = repository.update(profesor);
            
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
            List<Profesor> all = repository.findAll(pageable);
            if(all.isEmpty()){
                log.info("No tiene ningun elemento de esta tabla cargado en la base de datos");
            }else{
                for (Profesor profesor : all) {
                    log.info(profesor.getIdProfesor() + " : " + profesor.getCi() + " : " + profesor.getCorreo());
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
            Profesor find = repository.findId(101);
            if(find == null){
                log.info("No se encontro ningun curso con este ID");
            }else{
                log.info(find.getIdProfesor() + " : " + find.getCi() + " : " + find.getCorreo());
            }
            log.info("\n\n\t\t-------------------------------- Paso el test findById ------------------------------------------\n");
        } catch (RuntimeException e) {
            throw new RuntimeException("\n\n\t\t***************************** No paso el test findById ***************************************** \n" + e);
        }
    }
}
