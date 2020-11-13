package com.school.application.repository;

import java.util.List;

import com.school.application.component.TestDatabaseConfiguration;
import com.school.application.model.Persona;
import com.school.application.repository.Persona.PersonaRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
public class PersonaTest {
    
    @Autowired
    private PersonaRepository repository;

    Log log = LogFactory.getLog(getClass());
        
    Persona persona = new Persona();
    
    @Test
    public void saveTest(){
        try {
            persona.setNombre("New");
            persona.setaPaterno("New");
            persona.setaMaterno("Test");
            persona.setEdad(32);
            persona.setSexo("H");
            persona.setTelefono("44444444");
            repository.save(persona);            
        } catch(RuntimeException e){
            throw new RuntimeException("Error en el test Update Persona");
        }  
    }

    @Test
    public void updateTest(){
        try {
            persona.setCi(110);
            persona.setNombre("Update");
            persona.setaPaterno("Test");
            persona.setaMaterno("Test");
            persona.setEdad(32);
            persona.setSexo("H");
            persona.setTelefono("44444444");
            repository.update(persona);
        } catch(RuntimeException e){
            throw new RuntimeException("Error en el test Update Persona");
        }  

    }

    @Test
    public void findAllTest(){
        SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
        try{
            List<Persona> all = repository.findAll(pageable);
            if(all.isEmpty()){
                log.info("No tiene ningun elemento de esta tabla cargado en la base de datos");
            }else{
                for (Persona p : all) {
                    log.info(p.getCi() + " : " + p.getNombre());
                }            
            }   
        }catch(RuntimeException e){
            throw new RuntimeException("Error en el test FindAll Persona");
        }  
    }

    @Test
    public void findById(){
        try {
            Persona find = repository.findId(100);
            if(find == null){
                log.info("No se encontro ningun curso con este ID");
            }else{
                log.info(find.getCi() + " : " + find.getNombre());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutarse el Test findById Curso " + e);
        }
    }
}
