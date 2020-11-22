package com.school.application.repository;

import java.util.List;

import com.school.application.component.TestDatabaseConfiguration;
import com.school.application.model.Estudiante;
import com.school.application.repository.Estudiante.EstudianteRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
public class EstudianteTest {
    
    @Autowired
    private EstudianteRepository repository;

    Log log = LogFactory.getLog(getClass());

    @Test
    public void saveTest(){
        try {
            Estudiante estudiante = new Estudiante(); //ci, idCurso, correo, contrasena
            estudiante.setCi(127);
            estudiante.setIdCurso(1);
            estudiante.setCorreo("Nuevo");
            estudiante.setCorreo("Nuevo");
    
            boolean result = repository.save(estudiante);
    
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
            Estudiante estudiante = new Estudiante();
            estudiante.setCi(127);
            estudiante.setCorreo("Update");
            estudiante.setContrasena("modificada");

            boolean result = repository.update(estudiante);
            
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
            List<Estudiante> all = repository.findAll(pageable);
            if(all.isEmpty()){
                log.info("No tiene ningun elemento de esta tabla cargado en la base de datos");
            }else{
                for (Estudiante estudiante : all) {
                    log.info(estudiante.getIdCurso() + " : " + estudiante.getCi() + " : " + estudiante.getCorreo());
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
            Estudiante find = repository.findId(108);
            if(find == null){
                log.info("No se encontro ningun curso con este ID");
            }else{
                log.info(find.getIdCurso() + " : " + find.getIdEstudiante() + " : " +find.getCorreo());
            }
            log.info("\n\n\t\t-------------------------------- Paso el test findById ------------------------------------------\n");
        } catch (RuntimeException e) {
            throw new RuntimeException("\n\n\t\t***************************** No paso el test findById ***************************************** \n" + e);
        }
    }
}
